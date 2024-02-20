## akinkeskinbas ##

## Compose Instagram Story ##

### Step 1. Add the JitPack repository to your build file ###

```gradle
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
 ```

### Step 2. Add the dependency ###

```gradle
dependencies {
      implementation 'com.github.AkinKeskinbas:compose-instagram-story:Tag'
	}
```

## Example usage ##

```kotlin

                    InstagramStory(imageList = imageList) { page ->
                        Box(modifier = Modifier) {
                            AsyncImage(
                                model = imageList[page],
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
```
