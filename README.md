Windows Application Driver
Windows Application Driver (WinAppDriver) is a service to support Selenium-like UI Test Automation on Windows Applications. This service supports testing Universal Windows Platform (UWP), Windows Forms (WinForms), Windows Presentation Foundation (WPF), and Classic Windows (Win32) apps on Windows 10 PCs.

Install & Run WinAppDriver
Download Windows Application Driver installer from https://github.com/Microsoft/WinAppDriver/releases
Run the installer on a Windows 10 machine where your application under test is installed and will be tested
Enable Developer Mode in Windows settings
Run WinAppDriver.exe from the installation directory (E.g. C:\Program Files (x86)\Windows Application Driver)
Windows Application Driver will then be running on the test machine listening to requests on the default IP address and port (127.0.0.1:4723). You can then run any of our Tests or Samples. WinAppDriver.exe can be configured to listen to a different IP address and port as follows:

WinAppDriver.exe 4727
WinAppDriver.exe 10.0.0.10 4725
WinAppDriver.exe 10.0.0.10 4723/wd/hub
Note: You must run WinAppDriver.exe as administrator to listen to a different IP address and port.

Write an Automation Script
Now that you've successfully installed WinAppDriver, you can get started with authoring your first automation script!

Supported APIs
See here for a list of supported APIs by WinAppDriver. API support may differ from Appium and other counterparts.

FAQ & Documentation
Additional documentation on WinAppDriver and related topics can be found under /Docs/, such as the following:

Frequently Asked Questions
General Development & Best Practices
Using with Appium
Running WinAppDriver in CI (with Azure Pipelines)
Using UI Recorder
Authoring Test Scripts
Using the Selenium Grid
Running On a Remote Machine
Repository Content
This repository includes the following content:

Samples - used to showcase various commands and operations such as opening applications, finding elements, clicking elements, typing keystrokes, reading texts, etc; and can be run against built-in Windows 10 applications such as Alarms & Clock, Calculator, and Notepad.
Tests - used to verify the functionality of Windows Application Driver itself. The tests cover each API endpoints extensively and also against all basic UI control scenario, and demonstrate how to invoke certain command in C#. In addition, they show how to interact with some more complex UI elements such as DatePicker, SplitViewPane, Slider, etc.
UI Recorder - standalone tool that aims to provide users a simpler way of creating automaton scripts by recording UI events performed by the user and generating XPath queries and C# code on the fly. Read more about it on our Wiki.
Docs - subdirectory hosting WinAppDriver related documentation.
