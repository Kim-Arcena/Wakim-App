<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="assets/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Wakim App</h3>

  <p align="center">
    CMSC 22 Final Project
    <br />
    <a href="https://github.com/Kim-Arcena/Wakim"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/Kim-Arcena/Wakim">View Demo</a>
    ·
    <a href="https://github.com/Kim-Arcena/Wakim/issues">Report Bug</a>
    ·
    <a href="https://github.com/Kim-Arcena/Wakim/issues">Request Feature</a>
  </p>
</div>

<div>
  <!-- ABOUT THE PROJECT -->
  <h2>About the Project</h2>

  <a href="https://github.com/Kim-Arcena/Wakim">
      <img src="assets/banner.png" alt="Banner" width="1000" height="600">
    </a>

  We are tasked to develop a project where they will apply not only the basics of Java programming, but also Java design patterns, and best practices in Java.
  Preferably a project that is related to our hobbies or interests yet I decided to develop a project that could personally benefit me.

  <h3>Overview:</h3>

  <ul>
    <li>Native android Application featuring an alarm system that would force me to wake up by scanning a specific QR Code.</li>
    <li>CRUD application with Room Database</li>
    <li>Uses Java design patterns and best practices</li>
  </ul>
</div>

<div> 
  <!-- FEATURES -->
  <h2>Features</h2>
    <ol>
      <li>Record Alarm Time</li>
      <li>Alarm Manager and a Pending Intent are used to schedule an alarm.</li>
      <li>Using a Broadcast Receiver to start the Alarm Service.</li>
      <li>Manage alarm dismissal by Scanning a QR Code.</li>
      <li>Handle Alarm Enabling and Disabling</li>
      <li>Perform CRUD (Create, Read, Update, Delete) operations with Room Databse.</li>
    </0l>
</div>

<div>
  <!-- DESIGN PATTERNS -->
  <h2>Design Patterns</h2>
    <h3>MVVM Architectural Design Pattern</h3>
    <img src="assets/MVVM.png" alt="Splash Screen" width="100%" height="300">
    <p>Image Source: https://www.researchgate.net/figure/The-Model-View-ViewModel-MVVM-architectural-pattern-In-MVVM-the-View-layer-is_fig3_275258051 </p><br>
    <p>The app will be built using the <b>Model View ViewModel (MVVM) software design pattern.</b> This aids in the creation of a separation of considerations for components within your app, which simplifies the maintenance of your code base over time. Some of the Jetpack architecture components used in this app includes LiveData for enabling the MVVM design pattern, the Navigation architecture component to transition between screens in our app, and the Room persistence library for storing user data. When the user create, update and delete an alarm, the CreateAlarmFragment class use the MVVM design pattern to perform CRUD operations instances of the Alarm model into the Room Database via a ViewModel and a Repository.</p>
    <ol>
        <h3><li>Creational Design Pattern</li></h3>
            <h4>Factory Design Pattern</h4>
            <p> Static factory methods allow us to initialize and configure a new Fragment without using its constructor and extra setter methods. It is best practice to provide static factory methods for utilizing fragments since it encapsulates and isolates the actions necessary to setup the object from the client.</p>
            <h4><b>Singleton Design Pattern</b></h4>
            <p> When creating an AppDatabase object, the singleton design pattern is recommended. Each RoomDatabase instance is fairly expensice, and I hardly require access to numerous instances within a single process.</p>
        <h3><li>Behavioral Design Pattern</li></h3>
            <h4>Observer Design Pattern</h4>
            <p>The Observer pattern defines a one-to-many dependency between objects. When one object changes state, its dependents get a notification and updates automatically.
It can use it for operations of indeterminate time, such as API calls. I have used it to respond to user input using the onManageListener interface.</p>
        <h3><li>Structural Design Pattern</li></h3>
        <h4>Adapter Design Pattern</h4>
        <p>I used this design pattern to create dynamic alert list using RecyclerView (can found in alarmList package). Each RecyclerView has its own Adapter. The Adapter is in charge of mapping our models to their appropriate View components (ViewHolders), thereby adhering to the traditional Adapter design pattern definition. These two classes (Adapter and ViewHolder) collaborate to establish how your data is displayed. The ViewHolder is a View wrapper that includes the layout for a single item in the list. As needed, the Adapter generates ViewHolder objects and sets the data for those views.</p>
    </ol>
</div>

<div>
  <!-- BEST PRACTICES -->
  <h2>Best Practices</h2>
    <ul>
      <li>Proper naming conventions</li>
      <li>Averting from unnecessary objects creation</li>
      <li>Avoid redundant code blocks(initialization)</li>
      <li>Ordering Class Members by Scopes</li>
      <li>Organize codes by grouping packages according to usage</li>
      <li>Using the recommended android architecture</li>
      <li>Proper usage of Activity LifeCycle</li>
      <li>Utilized proven libraries </li>
    </ul>
</div>

<div>
  <!-- SETUP -->
  <h2>Setup</h2>
</div>

<div>
  <!-- APP SCREENSHOT -->
  <h2>Gallery</h2>
  <img src="assets/splashScreen.png" alt="Splash Screen" width="25%" height="420">
  <img src="assets/scheduleAlarm Activity.png" alt="Splash Screen" width="25%" height="420">
  <img src="assets/mainActivity.png" alt="Splash Screen" width="25%" height="420">
  <img src="assets/codeScanner.jpg" alt="Splash Screen" width="25%" height="420">
  
</div>

<div>
  <!-- JAVA DOCUMENTATION (JAVADOC) -->
  <h2>Java Documentation (JavaDoc)</h2>
</div>

<div>
  <!-- EXECUTABLE APK FILE -->
  <h2>Executable APK File</h2>
</div>

