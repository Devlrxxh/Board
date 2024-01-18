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

#### Setup
```java
public class ExamplePlugin implements JavaPlugin {

    @Override
    public void onEnable() {
        new Board(getInstance(), new ExampleScoreBoardAdapter());
    }

}
```
