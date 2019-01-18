# Logger

[ ![Download](https://api.bintray.com/packages/riningan/AndroidMaven/Logger/images/download.svg?version=1.1) ](https://bintray.com/riningan/AndroidMaven/Logger/1.1/link)

Android log util library.

D/DEBUG: <preffix>: time(2019-01-06: 21:22:55): process(2625): thread(1): MainActivity.onCreate
  
E/ERROR: <preffix>: time(2019-01-06: 21:22:55): process(2625): thread(1): MainActivity.onResume(19)


USAGE
---

### Gradle

Using Logger in your application.
Add dependencies in build.gradle of your module.

```groovy
dependencies {
  implementation 'com.riningan.util:logger:1.1'
}
```

### Application

On Android application class configure **Logger**:

```java
@Override
public void onCreate() {
    super.onCreate();
    Logger.Config
            .setDateFormat("yyyy-MM-dd: HH:mm:ss")
            .setEnabled(false)
            .removeApplicationId(BuildConfig.APPLICATION_ID)
            .addPreffix(BuildConfig.VERSION_NAME)
            .setOnMessageListener(new OnMessageListener() {
                @Override
                public void onNewMessage(MessageType type, String message) {
                }
            });
}
```

All settings is optional.

(**setDateFormat**) Default date format is **yyyy-MM-dd: HH:mm:ss**.

(**setEnabled**) By default **Logger** is enabled.

**removeApplicationId** removing application id from start of package name (com.foo.MainActivity.onCreate -> MainActivity.onCreate).

**addPreffix** add some text after DEBUG, ERROR and INFO.

**setOnMessageListener** is callback.

### Code

In code add:

```java
Logger.debug();
```

All variants:

```java
debug()
debug(String message)
debug(String param, String value)
debug(String param, boolean value)
debug(String param, int value)
debug(String param, long value)
debug(String param, float value)
debug(String param, double value)
debug(String param, String value, String... params)
error(String message)
error(Throwable throwable)
info()
info(String message)
```

If using super class use **forThis**.

### Without **forThis**

```java
class BaseFoo {
  void methodA() {
    Logger.debug();
  }
}

class Foo extends BaseFoo {
  @Override
  void methodA() {
    super.methodA();
    Logger.debug();
  }
}

Foo foo = new Foo();
foo.methodA();
```

D/DEBUG: : time(0000-00-00: 00:00:00): process(0): thread(0): BaseFoo.methodA

D/DEBUG: : time(0000-00-00: 00:00:00): process(0): thread(0): Foo.methodA

### With **forThis**

```java
class BaseFoo {
  void methodA() {
    Logger.forThis(this).debug();
  }
}

class Foo extends BaseFoo {
  @Override
  void methodA() {
    super.methodA();
    Logger.debug();
  }
}

Foo foo = new Foo();
foo.methodA();
```

D/DEBUG: : time(0000-00-00: 00:00:00): process(0): thread(0): Foo.methodA

D/DEBUG: : time(0000-00-00: 00:00:00): process(0): thread(0): Foo.methodA


LICENCE
-----

  	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	   http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
