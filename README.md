# Urban Mobility

### What I have learned
- Create unit, integration and end-to-end tests in Junit 5
- Using Jenkins to automate the build process

## Setting up pipeline for testing our project in dev environment
<br />

![image](https://github.com/rodercode/urban-mobility/assets/54941923/068e5439-a1cc-4a13-90b2-4068445158bf)
To add a new job, you need to press new item.
<br />
<br />
<br />

![image](https://github.com/rodercode/urban-mobility/assets/54941923/bf4fc827-df8b-417a-8fd8-3a8cbd8cf92c)
Enter your repository name and select Freestyle project
<br />
<br />
<br />

![image](https://github.com/rodercode/urban-mobility/assets/54941923/38e976bd-9216-4c3b-81c9-9debcde2aa9d)
Go to your job's configure and look for the Source Code Management section, select Git and enter your repository URL
In the Branches to build section, change */master to the branch you want Jenkins to test */dev
<br />
<br />
<br />

![image](https://github.com/rodercode/urban-mobility/assets/54941923/9ba97421-ea62-4c5f-b674-a99d17288bda)
In the Build Steps, press the button Add build step and select Execute Windows batch command
<br />
<br />
<br />

In the Command field, enter the following command:
    echo "STEP #1 - STARTING CLEANING STEP" 
    ./mvnw clean
Which will run this command every time you build your project in Jenkins

repeat step 4 and enter the following command:
    echo "STEP #2 - STARTING PACKAGE STEP" 
    ./mvnw package

Which will run this command every time you build your project in Jenkins

![image](https://github.com/rodercode/urban-mobility/assets/54941923/3b7c174b-5051-46e8-ae21-1c6516c715ef)
Return to Dashboard, press the job you just created, then Build.
<br />
<br />
<br />

## Setting up pipeline for testing our project in prod environment
![image](https://github.com/rodercode/urban-mobility/assets/54941923/6287d6a4-d661-4ec7-9b2e-aa312d48b53d)
![image](https://github.com/rodercode/urban-mobility/assets/54941923/fc13edd5-cf5d-4eed-8c38-4ebfd93b9c4e)

![image](https://github.com/rodercode/urban-mobility/assets/54941923/c3b8f623-dbd2-4544-8a07-3933f8f04cad)
![image](https://github.com/rodercode/urban-mobility/assets/54941923/88bd210b-17b1-4e45-b948-386be9a434c4)
![image](https://github.com/rodercode/urban-mobility/assets/54941923/2f4de45d-4971-41c0-8821-696d06fcea6b)
![image](https://github.com/rodercode/urban-mobility/assets/54941923/b026b2d1-ce9f-4773-a54c-07747e2de3e8)






