Week 1 / 03.24.2022

            We planned the project subject to be a viral epidemic simulator. I watched various videos on youtube about simulating epidemic. I learned about the 
        SIR model used in virus modeling. Despite the possibility of using a game engine, we got information from our TA about which engine we will use. I took a 
       course on virus modeling from Coursera and started watching the course. 
        Link of course : https://www.coursera.org/programs/ad118cb8-de48-4c25-ae9b-fffa2bf5d595/browse?currentTab=CATALOG&productId=FDsIaaCeEeiJEA5ThbYkCA&productType=course&query=SIR+model&showMiniModal=true
        I made progress in the course, but I did not have a chance to implement it on java, since the course is based on the R language.



Week 2 / 03.31.2022

            Since we decided to use the libgdx game engine to build our simulation, I purchased a course online to learn libgdx. I started learning libgdx 
        this week. I developed myself in libgdx and learned about the basic concepts such as sprite, body, application adapter, screen and other basic 
        classes. I developed myself in the field by developing libgdx projects.

        libgdx course link: https://www.udemy.com/course/the-complete-libgdx-game-course/


Week 3 / 04.07.2022

            This week I started working on two different projects with and without libgdx. Since I do not know much about libgdx, I continued to learn about the 
        game engine from the course I took from the internet. Since I am an amateur about libgdx, I could not make much progress in the project I used libgdx. In 
        the project where I don't use libgdx, I learned to run the code by writing the render with java's own libraries methods, and I wrote a small application 
        where the circles move in random directions at random speeds. Circles represented people.ü
            I also worked on requirements project with other group members.

Week 4 / 04.14.2022

        
            Although the project I wrote last week without using libgdx was successful in moving individuals, it was insufficient when detecting collisions 
        between people when the number of people increased to around three hundred. This problem is caused by the Java's standard library being unsuitable for writing games in this way.So I started to focus on the project I'm working on using libgdx.

Week 5 / 04.21.2022

            Using what I have learned so far about libgdx, I tried to do what I did in the project I developed without using libgdx, using libgdx. I've had 
        success in generating randomly moving individuals. I learned to write the algorithm that detects the collision and implemented it. The program worked 
        successfully, even with very high numbers of people, when not all people were in a row. Implementing the infection was easy as I was able to detect collision between persons. At this point, I laid the foundations of infection.


Week 6 / 04.28.2022

            I didn't get a chance to work on the project much this week. I learned about graph theory. I continued to develop myself in libgdx by examining libgdx 
            projects.
        

Week 7 / 05.05.2022

            I started working on the map for the first time. I extracted building images to put as an image in the render on the map. As a group, we decided that 
        the map would be a graph. Using pen and paper, I noted where the vertices of the graph were. We transferred the map online one by one with Faik. Writing 
        vertices and edges was a very long and arduous process. I started working on the pathfinder algorithm for the first time. We decided to use the Dijkstra 
        algorithm as the pathfinder in the simulation. I gained new knowledge about Dijkstra's algorithm. I learned that I can increase the speed of work by using 
        priority queue and some other techniques. I implemented the algorithm to give only the shortest distance.I had a problem in implementing Pathfinder, we 
        tried to overcome the problem with Faik. We solved the problem and managed to write an algorithm that returns the shortest path as a list of vertices. 
        After solving the problem, I worked on the task class in order to write people who can follow the given path. I wrote moving, waiting and waiting until 
        task classes. After these classes work properly, we wrote the routine classes with Faik. I and Faik have made great progress in simulation. Tuna, Gün and 
        Tarık also made improvements to the GUI. This week we got a working application by merging two big branches. I added the "day cycle" feature on the 
        weekend. Also, since it is costly to run the method called updateHealthCondition continuously in the render, I removed it from the program to write a 
        better one. I made changes to the routine of sick people. Finally, by working on mask and curfew mechanics with my friends, we managed to write primitive 
        versions.



Week 8 / 05.12.2022
            Since it was the last day of the project, I worked on the developments. I found and fixed various bugs. I wrote comments to various classes and took 
        part in the detailed design report. My day was spent making sure the program was working correctly and ensuring it was realistic. I also deleted the 
        unnecessary places we added in the classes, I made changes to make curfew work correctly. All day long, we worked with Faik, Tuna, Gün and Tarık to make 
        the final touches of the project.

