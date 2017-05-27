# Paulcasso

## Customized Use:


```
Drawable drawable = BlurredBitmap.getInstance(getApplicationContext())
                .loadDrawable(getResources().getDrawable(R.drawable.me))
                .centerCrop()
                .size(700, 200)
                .radius(100)
                .buildBlurredDrawable();

```

* **CenterCrop():** To crop the image in the middle
* **size(int width, int height):** To crop
* **radius(int radius):** The blurred value
* **start(int startX, int startY):** The crop start x and y coordinate

## Default Use:
```
Drawable drawable = BlurredBitmap.getInstance(getApplicationContext())
                .loadDrawable(getResources().getDrawable(R.drawable.wechat))
                .buildBlurredDrawable();

```

This will return the raw size of drawable.


## Gradle:
In your project build.gradle:

```
allprojects {
    repositories {
        jcenter()
        mavenLocal()
    }

    dependencies{
        repositories{
            maven { url 'http://146.118.101.148/repository/paulcasso/'}
        }
    }
}

```


In your module build.gradle:

```

dependencies {
	...
	...
    compile 'com.unimelb.itime:paulcasso:1.0.+'

}

```

