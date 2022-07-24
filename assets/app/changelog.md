Changelog for 1.6.8
===================
## Additions:

- Delete local replay
- Custom playfield size ranging from 80%-100%, so that you can keep your fingers away from system status bar
- Sudden Death (SD) mod: triggers fail after 1 miss, slider breaks will not trigger fail (unranked)
- Small Circle (SC) mod: CS + 4 (unranked)
- Perfect (PF) mod: SS or fail (unranked)
- Flashlight (FL) mod: lightened area might be different from osu! PC. (unranked)
- Really easy (RE(z)) mod: Easier EZ, only decreases AR slightly instead of cutting in half (unranked)
- ScoreV2 mod: no change to slider judgement, uses score calculation similar to osu!lazer (unranked)
- Speed modifier (adjusts a map's speed between 0.5x and 2x, able to stack on top of DT/NC/HT) (unranked)
- Force AR (adjusts a map's AR anywhere from 0 to 12.5, ignores effect from all mods) (unranked)
- User panel (tap your profile in main menu to go directly to your profile page)
- Internal audio recording support for Android 10+
- Option to remove slider lock and spinner lock (scores made with this option enabled will be unranked)
- Exit dialog prompt when you want to exit the game
- Sort beatmap by stars and length
- Search beatmap based on difficulty name
- Multiple input support in one frame, making double-tap possible
- Animated hit0, hit50, hit100, hit300 (using these elements animated will differentiate them from their displayed element on result screen)
- Random welcome sound
- Customizable exit sound via skins
- Warning message if your storage is low to prevent replay/score corruption (will cause bugs like unwatchable replays and inability to watch local replays)
- Option to precalculate slider path (improves performance during gameplay, but increases map loading time)
- Option to calculate pp when opening a score
- Option to hide replay text when watching a replay
- Option to save failed replays

## Changes:

- PR (Precise) mod is ranked
- EZ (Easy) mod has 3 lives
- RX (Relax) mod and AP (AutoPilot) mod replays will be saved with 0.01x score multiplier
- Updated star rating system (improves calculation accuracy to be closer to osu! PC star rating)
- 10 simultaneous cursor input support
- Improved framerate for storyboard
- Custom skins are sorted alphabetically
- Rewritten beatmap parser, now able to load some beatmaps that were not possible to load
- Sliders will gradually fade out if HD (Hidden) mod is active

## Bug fixes:

- Fixed a bug where follow points that are too small will make the game lag
- Fixed a bug where multiple overlapping notes can be hit by only tapping once
- Fixed a bug where NC (NightCore) mod speed multiplier is not the same as DT (DoubleTime) mod in some beatmaps
- Fixed a bug where sliders with negative length or infinite BPM makes a beatmap unplayable
- Fixed a bug where some beatmaps can crash the game when tapping a note due to out of bound hitsounds
- Fixed a bug for spinners (spinners with negative length or less than 50ms will spin automatically)
- Fixed a bug where some slider ticks fail to display correctly
- Fixed a bug where very high velocity reverse sliders (buzz sliders) have incorrect length
- Fixed a bug where certain skin sound elements will crash the game (audio files smaller than 1 KB will be ignored)
- Fixed SD card bug by moving library cache file to the game's private directory
- Fixed a bug in navigation bar (should disable properly)
- Fixed a bug where score would show incorrectly above 100 million during gameplay
- Fixed a bug where beatmap length greater than 1 hour is displayed incorrectly
- Fixed a bug where full combo count in some beatmaps are inconsistent due to precision error in slider tick calculation
- Fixed a bug where combo count stays at 0 if complex effect is disabled without restarting the game
- Fixed a bug where score goes to negative value beyond 2,147,483,647 (score will be capped at said value)
- Fixed a bug where an ill-formed beatmap can crash the game during import process
- Fixed a bug where custom directories are not loaded properly
- Fixed a bug where player avatars in online leaderboard are not loaded when not using Wi-Fi connection
- Fixed a bug where re-watching replays can crash the game
- Fixed a bug where custom beatmap skin's hitcircle texture does not apply to sliders if not overridden
- Fixed a bug where background music volume setting does not apply in song selection menu
- Fixed a bug where some mutually exclusive mods can be selected together
- Fixed a bug where offline replays do not get saved
- Fixed a bug where beatmaps that haven't finished downloading gets imported

## Removals:

- Removed split-screen support as the game restarts if used in split screen mode

## Additions [+], changes [=], bug fixes [*], and removals [-] since the previous pre-release:

- [+] Added slider dim for FL (Flashlight) mod
- [+] Added break dim for FL (Flashlight) mod
- [+] Added the option to save failed replays
- [=] Sliders will gradually fade out if HD (Hidden) mod is active
- [=] FL (Flashlight) mod dim area now starts at the center of the screen
- [=] Moved PP information in score result scene to left-bottom corner
- [*] Fixed a bug where offline replays do not get saved
- [*] Fixed a bug where FL (Flashlight) mod dim area flickers if the player taps during break
- [*] Fixed a bug where beatmaps that haven't finished downloading gets imported
- [*] Fixed a bug where star rating calculation does not take speed multiplier into account
- [*] Fixed a bug where some animated hit results (100k, 300k, and 300g) are not displayed properly
- [-] Removed split-screen support as the game restarts if used in split screen mode