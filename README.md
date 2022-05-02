
## Cute

A Minecraft 1.8.9 mod for rendering tracers, esp, and the like.

This mod aims to be client-side only, and also doesn't use any mixins (that i'm aware of)

### Features 

-Block ESP
-Entity ESP
-Tracers 
-No Render (disabled fog only)
-Projectile Tracers

### Modding Setup

You shouldn't need to download forge because I think the gradlew.bat is enough, if not see [this](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.8.9.html) (I used the recommended MDK)

I'm using [jdk-8](https://adoptium.net/temurin/releases), but Eclipse also needs some higher version to even open, so I also have [jdk-17](https://adoptium.net/temurin/releases).

If you can't open Eclipse because the jdk version do the following:
- Open the start menu
- Search for and open 'View advanced system settings'
- Then click 'Environment Variables'
- Under 'System variables' double click the value for 'JAVA_HOME' and make sure it points to "C:\Program Files\Eclipse Adoptium\jdk-17...-hotspot" ( or whereever you installed jdk-17)


Clone the repo and a terminal in the folder. (you can see for more details [README.txt](./gradlew-instructions.txt))

Then run 
```
./gradlew.bat setupDecompWorkspace
```

Once that's done doing whatever it does, if you're using Eclipse run
```
./gradlew.bat eclipse
```

Then open Eclipse and choose the 'eclipse' folder as the project.

Try running the project with the green arrow in the top left ( or make use of the 'run' toolbar)

If it doesn't run you probably need to set the jdk version in Eclipse.

To set the jdk version do the following:

hit `Window > Preferences > Java > Installed JREs > Add > Standard VM` and set the JRE home directory to the installation of jdk-8 from above (probably something like C:\Program Files\Eclipse Adoptium\jdk-8...-hotspot)

You should be able to run project now.