const fs = require('fs');
const file = 'app/src/main/java/com/example/pao/data/PAOData.kt';
let content = fs.readFileSync(file, 'utf8');

const M = {
"75":"Leonardo da Vinci, the Renaissance genius, sketches the Mona Lisa with a ski-pole-shaped brush while a carabiner-shaped palette hangs from his belt. Smell the oil paint and old parchment. Taste the bitterness of his own focus. Feel the brush's fine tip on the canvas. Hear the scratch of bristles as her smile appears. He steps back — 'Perfection.'",
"76":"Virginia Woolf, the modernist writer, writes in her diary with a shepherd's-staff-shaped pen while a key-fob-shaped pendant dangles. Smell the ink and old paper. Taste the salt of her tears on her lips. Feel the pen's cold metal. Hear the scratch of the nib on paper. She writes: 'I am rooted, but I flow.'",
"77":"Mozart, the composer, sits at a piano with two crossed piano-leg-shaped pedals. His fingers fly across the keys. Smell the wood and rosin. Taste the sweetness of the music on his tongue. Feel the keys' ivory under his fingers. Hear the symphony rise as he plays. He smiles — a genius at work.",
"78":"Amelia Earhart, the aviator, grips the umbrella-shaft-shaped controls of her biplane while her goggle-lenses-shaped eyes scan the horizon. Smell the fuel and leather. Taste the cold wind on her lips. Feel the plane vibrate under her hands. Hear the engine roar. She yells 'I'M GOING AROUND THE WORLD!' as the ocean glitters below.",
"79":"Bruce Lee, the martial arts legend, wields a lightning-rod-shaped nunchuck with a yo-yo-string-shaped chain. He spins it faster than the eye can follow. Smell the sweat and sandalwood. Taste the focus in his mouth. Feel the nunchuck's wood in his hands. Hear the whoosh of each swing. He yells 'BE WATER, MY FRIEND!' as the chain snaps and the nunchuck flies.",
"80":"Picasso, the Cubist painter, fragments a guitar into pieces shaped like two stacked mushrooms sitting on a fried-egg-shaped palette. He glues the pieces back sideways. Smell the paint and turpentine. Taste the bitterness of his own genius. Feel the shattered wood in his hands. Hear him laugh as the guitar screams 'HELP ME!' Picasso calls it art and ignores it.",
"81":"Han Solo, the smuggler, fires his billiard-cue-shaped blaster at a Stormtrooper, his wide-open eyes scanning the cantina. Smell the smoke and sweat. Taste the adrenaline on his tongue. Feel the blaster's grip in his hand. Hear the shot and the Stormtrooper fall. He yells 'I SHOOT FIRST!' and runs.",
"82":"Obi-Wan Kenobi, the Jedi Master, vanishes into the Force with a figure-skater-spin-shaped flourish as his guitar-neck-shaped lightsaber falls. He becomes one with the Force. Smell the scorched metal. Taste the calm on his tongue. Feel the Force ripple through the air. Hear him whisper 'If you strike me down, I shall become more powerful than you can possibly imagine.' He vanishes.",
"83":"Zorro, the masked vigilante, slices a clam-shell-ridge-shaped 'Z' mark into a wall with his sword as stacked-coin-shaped sparks fly. Smell the blood and dust. Taste the satisfaction on his tongue. Feel the sword's blade in his hand. Hear the 'Z' sing into the wall. He bows and disappears into the night.",
"84":"Mulan, the warrior, swings a jester-bells-shaped sword while a fence-corner-shaped shield deflects arrows. She rides into battle. Smell the smoke and blood. Taste the courage on her tongue. Feel the horse surge beneath her. Hear her battle cry as she charges. The enemy scatters.",
"85":"Ragnar Lothbrok, the Viking king, holds a double-scoop-ice-cream-shaped axe while a lock-latch-shaped shield hangs from his arm. He charges into battle. Smell the blood and sea salt. Taste the iron of his own conviction. Feel the axe's weight in his palm. Hear his war cry as his longship hits the beach.",
"86":"Blackbeard, the pirate, holds a licorice-twist-shaped map while an infinity-loop-shaped compass spins in his hand. He laughs as his ship approaches. Smell the gunpowder and rum. Taste the salt on his lips. Feel the ship's deck under his boots. Hear his crew roar as the cannons fire.",
"87":"Genghis Khan, the conqueror, swings a crowbar-shaped sword while bongo-drum-shaped war drums beat behind him. He rides into battle. Smell the dust and blood. Taste the iron in his mouth. Feel the horse's mane under his hand. Hear his war cry as he charges. The enemy scatters.",
"88":"Achilles, the Greek warrior, clashes two round shields together — CLANG! CLANG! He roars. Smell the blood and sweat of Troy. Taste the iron of his own bitten lip. Feel the leather grip of each shield in his palms. Hear the shields clash again. He screams a war cry as a Trojan falls. His heel — his only weak spot — twitches behind him, forgotten.",
"89":"Socrates, the philosopher, leans on a bell-curve-tail-shaped cane while a wobbly-wheel-shaped chariot rolls past. He debates. Smell the olive oil and dust. Taste the bitter hemlock on his tongue. Feel the cane's worn wood in his hand. Hear his voice rise: 'The unexamined life is not worth living!' The chariot rolls on.",
"90":"Nostradamus, the astrologer, watches a crystal-ball-shaped orb float on a balloon-on-string-shaped tether. He sees the future. Smell the incense and old books. Taste the bitter herbs on his tongue. Feel the orb's cold glass in his hand. Hear the future whisper — 'The end is near!' — as the orb cracks.",
"91":"Spartacus, the gladiator, swings a snail-shell-shaped trident while a baseball-bat-shaped gladius flashes in his other hand. He roars into battle. Smell the sand and blood. Taste the iron on his tongue. Feel the trident's wooden shaft in his hand. Hear the crowd roar as he charges. His enemy falls.",
"92":"Frankenstein's Monster, the reanimated creature, holds a yo-yo-string-shaped chain while a vine-tendril-shaped surgical tool hangs from his hand. He lurches forward. Smell the formaldehyde and rot. Taste the bitter metal on his tongue. Feel the chain's cold links in his palm. Hear him groan as lightning strikes the lab — and he rises.",
"93":"Jekyll and Hyde, the dual personality, transforms with a cat's-curled-tail-shaped grin while a curled-chest-shaped potion bubbles in his hand. He drinks it. Smell the chemicals and sweat. Taste the bitterness on his tongue. Feel the transformation tear through his body. Hear him scream as Hyde takes over.",
"94":"Nosferatu, the silent-film vampire, lifts a rolled-ribbon-shaped coffin lid while a ski-pole-shaped claw reaches for you. He screeches. Smell the rot and old blood. Taste the fear in your throat. Feel the cold draft from the coffin. Hear his scream as he rises from the tomb.",
"95":"Rasputin, the mad monk, drinks poison from a bicycle-bell-shaped goblet while a fishhook-shaped cross hangs from his neck. He smiles. Smell the poison and incense. Taste the bitter cyanide on his tongue. Feel the poison burn his throat. Hear him laugh: 'I am still alive!' — and he drinks more.",
"96":"Medea, the sorceress, sprinkles a lollipop-stick-curve-shaped wand over a shepherd's-crook-shaped Golden Fleece. It glows. Smell the magic and incense. Taste the honey-sweet spell on her tongue. Feel the wand's warmth in her hand. Hear her chant as the fleece turns gold.",
"97":"Odysseus, the Greek king, sails with a fishing-bobber-shaped compass while a cutlass-shaped sword hangs from his belt. He commands his ship. Smell the salt and sea. Taste the brine on his lips. Feel the ship's wheel in his hands. Hear his crew row as he yells 'ROW! ROW FOR YOUR LIVES!'",
"98":"Beowulf, the hero, grips a magnifying-handle-shaped sword while a jar-lid-on-jar-shaped shield hangs from his arm. He charges at Grendel. Smell the blood and mead. Taste the iron on his tongue. Feel the sword's hilt in his hand. Hear him yell 'I AM BEOWULF!' as he rips Grendel's arm off.",
"99":"Persephone, the Greek goddess, holds two pomegranates, one in each hand, as she descends the stairs into the underworld. Smell the ash and pomegranate juice. Taste the sweet seeds on her tongue. Feel the cold stone of the stairs. Hear the gates of the underworld close behind her. She is queen now."
};

let replaced = 0;
for (const [num, mnemonic] of Object.entries(M)) {
  const regex = new RegExp(`(PAOEntry\\("${num}"[\\s\\S]*?,\\s*")([^"]*)("\\),)`, 'm');
  if (regex.test(content)) {
    content = content.replace(regex, (m, p, o, s) => p + mnemonic.replace(/"/g, '\\"') + s);
    replaced++;
  } else {
    console.log(`⚠️ Missing: ${num}`);
  }
}
fs.writeFileSync(file, content);
console.log(`✅ Batch 4: Replaced ${replaced}/25 mnemonics`);
