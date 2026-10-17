const fs = require('fs');
const file = 'app/src/main/java/com/example/pao/data/PAOData.kt';
let content = fs.readFileSync(file, 'utf8');

const M = {
"50":"King Arthur pulls Excalibur from a gold medal-shaped stone, his curled fist gripping the hilt. The sword glows as the stone shatters. Smell the wet earth and ancient magic. Taste the copper of the sword on his tongue. Feel the cold pommel in his palm. Hear the blade sing as it comes free. He raises it high, and light floods the courtyard.",
"51":"Medusa, the Gorgon, flicks her forked tongue like a question-mark tail as she holds a thermometer-shaped mirror. She stares at her own reflection. Smell the swamp and stone. Taste the venom in her mouth. Feel the snakes writhing on her head. Hear them hiss as the mirror cracks. She screams, and the mirror shatters — but her reflection is already stone.",
"52":"Anubis, the jackal-headed god, holds a genie-lamp-shaped scale and a leopard-tail feather. He weighs a heart against the feather. Smell the incense and dust. Taste the tomb air on his tongue. Feel the scale's cold balance in his hand. Hear the feather settle, and the scales tip. The heart is pure — or is it? He growls, and the chamber darkens.",
"53":"Icarus, the boy of wax wings, beats his fig-roll-shaped wings as he climbs a staircase of clouds toward the sun. Smell the melting wax and burning feathers. Taste the salt of his own sweat. Feel the sun scorch his back. Hear his father scream 'ICARUS!' as the wax drips. He falls — feathers scattering — into the sea.",
"54":"Godzilla, the kaiju, stomps down a diving-board-shaped tail onto a toll-booth-shaped building. The building crushes under his foot. Smell the fire and smoke. Taste the ash on his tongue. Feel the ground shake. Hear him roar as Tokyo Tower bends. Cars fly through the air like toys.",
"55":"The Invisible Man, wrapped in a raincoat, leaves two hooked-hand-shaped gloves floating in midair. He looms over you, unseen. Smell the damp wool and rain. Taste the metallic tang of fear. Feel the cold air shift as he moves. Hear his breath — but see nothing. He laughs from somewhere, and the gloves twitch.",
"56":"Quetzalcoatl, the feathered serpent, uncoils from a banana-peel-shaped twist as he spirals up a kettle-spout-shaped pyramid. Smell the jungle and rain. Taste the sweet fruit on his tongue. Feel the feathers brush your cheek. Hear his rattle-like hiss. He coils at the top and spreads his wings — light glinting off his scales.",
"57":"Mothman, the cryptid, hovers with ice-cream-scoop-shaped wings while a crowbar-shaped antenna sways. His red eyes glow. Smell the ozone and river water. Taste the cold on your tongue. Feel the dread in your stomach. Hear the flap of his wings. He points at the bridge — the Silver Bridge — and vanishes.",
"58":"Bigfoot, the cryptid, turns a lock-latch-shaped stick as he stomps past stacked-tire-shaped rocks. His footprints sink deep. Smell the pine and fur. Taste the moss on his tongue. Feel the earth shake. Hear the twig snap under his foot. He stops and looks at you — then vanishes into the trees.",
"59":"La Llorona, the Weeping Woman, clicks her tongue like a hooked lasso as she stares into a river of tears. She wails. Smell the river water and wet earth. Taste the salt of her own tears. Feel the grief in her chest. Hear her cry '¡Ay, mis hijos!' as the river swells.",
"60":"Dracula, the vampire, curls his shepherd's-crook-shaped fangs into a glass-marble-shaped blood orange. He sucks it dry. Smell the iron and old blood. Taste the copper on his lips. Feel the orange burst in his hand. Hear him hiss as the juice drips down his chin. He wipes his mouth and vanishes into the night.",
"61":"Kali, the Hindu goddess, files her nails with a nail-file-shaped blade as a rope-knot-shaped necklace jangles. She holds a severed head. Smell the blood and incense. Taste the copper on her tongue. Feel the blade bite her nail. Hear her laugh as the head rolls away. Her multiple arms wave in the smoky light.",
"62":"Fenrir, the giant wolf, bares his fox-ear-shaped fangs as he bites a chain wrapped around a round-belly-shaped boulder. He snarls. Smell the wolf musk and iron. Taste the metal on his tongue. Feel the chain bite into his jaw. Hear him howl as the chain snaps. The gods tremble in Asgard.",
"63":"The Banshee, the Irish spirit, shrieks with a bell-curve-shaped mouth into a bent-bow-shaped megaphone. Her wail pierces the night. Smell the graveyard and fog. Taste the salt of her tears. Feel the chill in the air. Hear her cry echo across the moors. A family somewhere hears her — and knows someone will die.",
"64":"Sun Wukong, the Monkey King, transforms his kettle-spout-shaped staff into an arrowhead-shaped spear as he rides a cloud. He laughs. Smell the peaches and rain. Taste the sweet fruit on his tongue. Feel the cloud under his feet. Hear him shout 'I AM THE GREAT SAGE!' as he swings the spear.",
"65":"Cthulhu, the cosmic horror, rises from the sea with a teacup-handle-shaped tentacle curling around a door-knocker-shaped tome. His presence warps reality. Smell the saltwater and rot. Taste the madness on your tongue. Feel your mind crack. Hear him groan — a sound that shatters glass. You scream, but no sound comes out.",
"66":"Kitsune, the fox spirit, weaves two rolled-newspaper-shaped tails as she spins a jump-rope-loop-shaped ring of fire. She transforms. Smell the incense and fur. Taste the smoke on your tongue. Feel the heat of the flames. Hear her laugh as she becomes a beautiful woman. The fire ring closes and she bows.",
"67":"The Wendigo, the cannibal spirit, drips safety-pin-curl-shaped icicles as it climbs a slanted-roof-shaped tree. It shrieks. Smell the frozen meat and pine. Taste the frost on your tongue. Feel the cold burn your skin. Hear the branches snap under its weight. It spots you — and grins.",
"68":"The Valkyrie, the warrior maiden, rides a whale-spout-shaped cloud as two cherry-shaped gems dangle from her spear. She descends. Smell the storm and iron. Taste the rain on your lips. Feel the wind tear at your clothes. Hear her battle cry as she scoops up a fallen warrior and carries him to Valhalla.",
"69":"Cerberus, the three-headed dog, curls his ear-tip-shaped tails around a padlock-curve-shaped gate. He growls from three mouths. Smell the sulfur and blood. Taste the fear in your throat. Feel the ground shake under his paws. Hear all three heads bark at once — and the gates of the underworld slam shut.",
"70":"Frida Kahlo, the Mexican painter, balances a stack-of-pancakes-shaped palette as she leans on a walking-cane-shaped brush. She paints her own face. Smell the oil paint and turpentine. Taste the bitterness of her lipstick. Feel the brush in her hand. Hear the brushstroke on canvas as she adds another flower to her hair.",
"71":"Nikola Tesla, the inventor, swings a scythe-blade-shaped coil as he leans on a walking-stick-shaped metal pole. Electricity arcs from his hands. Smell the ozone and burnt copper. Taste the metallic charge on his tongue. Feel the static lifting his hair. Hear him yell 'THE POWER OF THE UNIVERSE!' as lightning cracks the sky.",
"72":"Marie Curie, the physicist, stands at a cliff-edge-shaped precipice, her elbow-crook-shaped arm holding a glowing vial of radium. It burns green. Smell the chemicals and radiation. Taste the bitter metal on her tongue. Feel the heat of the vial through her glove. Hear the Geiger counter click faster. She smiles. 'This will change the world.'",
"73":"H.P. Lovecraft, the horror writer, swings a golf-club-shaped pen as a rope-coil-shaped tendril crawls across his desk. He writes furiously. Smell the ink and old paper. Taste the madness on his tongue. Feel the cold tendril on his wrist. Hear him whisper 'Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn' as the room darkens.",
"74":"Joan of Arc, the French heroine, raises a hockey-stick-shaped banner as an oar-blade-shaped sword flashes in her other hand. She charges into battle. Smell the smoke and blood. Taste the iron of her own conviction. Feel the horse surge beneath her. Hear her cry 'FOR FRANCE!' as the enemy scatters."
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
console.log(`✅ Batch 3: Replaced ${replaced}/25 mnemonics`);
