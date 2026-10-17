const fs = require('fs');
const oldFile = fs.readFileSync('old_PAOData.kt', 'utf8');
const currentFile = fs.readFileSync('app/src/main/java/com/example/pao/data/PAOData.kt', 'utf8');

const bioMap = {};
const bioRegex = /PAOEntry\("(\d{2})",\s*"[^"]*",\s*"[^"]*",\s*"[^"]*",\s*"[^"]*",\s*"[^"]*",\s*"((?:[^"\\]|\\.)*)"/g;
let match;
while ((match = bioRegex.exec(oldFile)) !== null) {
  bioMap[match[1]] = match[2];
}
console.log(`Found ${Object.keys(bioMap).length} long bios in old file`);

let updated = currentFile;
let count = 0;
for (const [num, longBio] of Object.entries(bioMap)) {
  const regex = new RegExp(
    `(PAOEntry\\("${num}",\\s*"[^"]*",\\s*"[^"]*",\\s*"[^"]*",\\s*"[^"]*",\\s*"[^"]*",\\s*")[^"]*(")`,
    'm'
  );
  if (regex.test(updated)) {
    updated = updated.replace(regex, (m, p1, p2) => {
      count++;
      return `${p1}${longBio}${p2}`;
    });
  } else {
    console.log(`⚠️ Could not match bio for ${num}`);
  }
}

fs.writeFileSync('app/src/main/java/com/example/pao/data/PAOData.kt', updated);
console.log(`✅ Restored ${count}/100 long bios`);
