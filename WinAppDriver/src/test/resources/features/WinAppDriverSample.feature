Feature: Validate Calculator Application using WinAppDriver

@Calculator1
  Scenario: Validate Calculator using WinAppDriver
    Given Launch Calculator App
    When User enters values to add
    Then Validate the Result for addition
    
@Calculator
  Scenario: Validate Calculator using WinAppDriver
    Given Launch Calculator App
    When User enters values to subtract
    Then Validate the Result for subtraction
 
