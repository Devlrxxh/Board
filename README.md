# Board

> [!IMPORTANT]
> To use Board your server must be running on a 1.8 server!

## Features
* No flickering
* Works with all versions from 1.7.x to 1.8.x
* Very Light Weight
* Easy to use
* Dynamic scoreboard size: you don't need to add/remove lines, you can directly give a string list (or array) to change all the lines
* Can be used asynchronously
* Supports up to 40 characters per line
* Change the scoreboard size easily by using a list of strings – no need to add or remove lines one by one.

## Code Examples

#### Board Setup
```java
public class ExamplePlugin implements JavaPlugin {

    @Override
    public void onEnable() {
        new Board(getInstance(), new ExampleScoreBoardAdapter());
    }

}
```

#### Board Adapter
```java
public class ScoreboardProvider implements BoardAdapter {
    @Override
    public String getTitle(Player player) {
        return "&7Title";
    }

    @Override
    public List<String> getLines(Player player) {
        final List<String> toReturn = new ArrayList<>();

        toReturn.add("&a&lLine 1!");
        toReturn.add("&c&lLine 2!");
        toReturn.add("&9&lLine 3!");

        return toReturn;
    }

    @Override
    public Long getDelay() {
        return 2L;
    }
```
