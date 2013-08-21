## Dagger - a light, robust Web UI autotest framework

Dagger is a light, robust Web UI autotest framework based on [Selenium](http://seleniumhq.org/) and [TestNg](http://testng.org/doc/index.html).（中文介绍参见[这里](https://github.com/NetEase/Dagger/wiki/Dagger)）

> Dagger is an automation framework first, it provides APIs to control browsers;
> 
> Dagger is a test framework then, it uses TestNg to organize testcases and TestNg's assertions are embedded in APIs above;
> 
> Dagger is a design style at last: the framework and the testcases based on it both should be light and straightforward.

* Wiki: <https://github.com/NetEase/Dagger/wiki>
* Issues: <https://github.com/NetEase/Dagger/issues>
* Javadoc: <http://netease.github.com/Dagger>
* Tags: Selenium, TestNg, autotest

## Features

* Easy to learn while only less then 20 APIs altogether, see [this](http://netease.github.com/Dagger/classcom_1_1netease_1_1dagger_1_1_browser_emulator.html).
* Providing a quickstart, see [this](https://github.com/NetEase/Dagger/wiki/Quick-Start).
* High speed with parallel mode which is indeed TestNg's feature, see [this](https://github.com/NetEase/Dagger/wiki/Parallel-Mode)
* High stability with a trick on TestNg to retry failed/skipped testcases automatically, see [this](https://github.com/NetEase/Dagger/wiki/Retry-Failed-Or-Skipped-Testcases).
* Using Chrome as default browser which is much more quick and stable than Firefox and IE. 
* Firefox and IE are also supported.
* Capture screenshot automatically when testcase failed.

## Coming soon

* Integrating Flex/Flash automation.

## How to use Dagger

Dagger is quite suitable for a small group to kick off Web UI autotest, for this case, just checkout Dagger with Eclipse and write testcases within it.

Already have an autotest framework? Please build Dagger into a .jar file to use, see [this](https://github.com/NetEase/Dagger/wiki/FAQ) 

## Contributors

* NetEase, Inc.

## License

(The Apache License)

Copyright (c) 2012-2013 NetEase, Inc. and other contributors

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
