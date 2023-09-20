### Urban Mobility

## What I have learned
- Create unit, integration and end-to-end tests in Junit 5
- Using Jenkins to automate the build process

## How to configure Jenkins to run tests

# Setting up pipeline for testing in dev environment
![image](https://github.com/rodercode/urban-mobility/assets/54941923/17eefa3e-1853-415c-84a9-135fe3201506)

    
1. Add new jobs, enter your repository name and select Freestyle project
2. In the Source Code Management section, select Git and enter your repository URL
3. In the Branches to build section change */master to */dev
4. In the Build Steps press button Add build step and select Execute Windows batch command
5. In the Command field enter the following command:
    echo "STEP #1 - STARTING CLEANING STEP" 
    ./mvnw clean
6. repeat step 4 and enter following command:
    echo "STEP #2 - STARTING PACKAGE STEP" 
    ./mvnw package
7. go back to Dashboard, press the job you just created and then press build.

    

