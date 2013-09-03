## Dagger - a light, robust Web UI autotest framework

Dagger is a light, robust Web UI autotest framework based on [Selenium](http://seleniumhq.org/) and [TestNG](http://testng.org/doc/index.html).（中文介绍参见[这里](https://github.com/NetEase/Dagger/wiki/Dagger)）

> Dagger is an automation framework first, it provides APIs to control browsers;
> 
> Dagger is a test framework then, it uses TestNG to organize testcases and TestNG's assertions are embedded in APIs above;
> 
> Dagger is a design style at last: the framework and the testcases based on it both should be light and straightforward.

* Wiki: <https://github.com/NetEase/Dagger/wiki>
* Issues: <https://github.com/NetEase/Dagger/issues>
* Javadoc: <http://netease.github.com/Dagger>
* Quick Start: <https://github.com/NetEase/Dagger/wiki/Quick-Start>
* Tags: Selenium, TestNG, autotest

## Features

* Easy to learn while only less than 20 APIs altogether, see [this](http://netease.github.com/Dagger/classcom_1_1netease_1_1dagger_1_1_browser_emulator.html).
* High speed with parallel mode which is indeed TestNG's feature, see [this](https://github.com/NetEase/Dagger/wiki/Parallel-Mode)
* High stability with a trick on TestNG to retry failed/skipped testcases automatically, see [this](https://github.com/NetEase/Dagger/wiki/Retry-Failed-Or-Skipped-Testcases).
* Use Chrome as default browser which is much quicker and more stable than all Firefox, IE and Safari. 
* Firefox, IE and Safari are also supported.
* Capture screenshot automatically when testcase fails.
* Support data-driven testing.
* Contrast images and compare differences by crawling page elements and screenshots on the pixel level.


## Coming Soon

* Integration with Flex/Flash automation.

## How to Use

Dagger is quite suitable for a small group to kick off Web UI autotest. For this case, just checkout Dagger and configure maven with Eclipse and then write testcases with it.

Already have an autotest framework? Please build Dagger into a .jar file before use, the steps are as follows: 
*  Checkout Dagger's source code
*  Enter the root directory
*  Run `mvn clean package -DskipTests` in terminal

Then fetch `dagger-1.2.jar` under `target` folder, or just [download]() the `dagger-1.2.jar`. See [this](https://github.com/NetEase/Dagger/wiki/FAQ) for more details.

Currently, we use `selenium-server-standalone-2.37.0.jar` and `selenium-safari-driver-2.37.0.jar` , you can change the configuration in the _pom.xml_.

By the way, you should download the [chromedriver_for_win_2.3.exe](http://chromedriver.storage.googleapis.com/2.3/chromedriver_win32.zip) and [iedriver_win32_2.37.0.exe](http://selenium.googlecode.com/files/IEDriverServer_Win32_2.37.0.zip) if necessary. We suggest to put the .exe files in the `res` folder. 

## Contributors

* NetEase, Inc.
* chenDoInG

## How to Contribute

You are welcome to contribute to Dagger as follow

* add/edit wiki
* report/fix issue
* code review
* commit new feature
* add testcase/demo

Meanwhile you'd better follow the rules below

* It's *NOT* recommended to submit a pull request directly to Dagger's `master` branch. `develop` branch is more appropriate
* Follow common Java coding conventions
* Put all Java class files under *com.netease* package
* Add the following [license](#license) in each Java class file

## License

(The Apache License)

Copyright (c) 2012-2013 NetEase, Inc. and other contributors

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
