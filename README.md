# TheaterTicketDispenser

## :scroll: Table of Contents

* [Introduction](#performing_arts-introduction)
  * [Start-up the application](#rocket-start-up-the-application)
* [Design](#black_nib-design)
  * [Class Diagrams](#european_post_office-class-diagrams)
  * [Sequence Diagrams](#chart_with_upwards_trend-sequence-diagrams)
  * [Activity Diagrams](#running-activity-diagram)
* [Implementation](#floppy_disk-implementation)
  * [About the page](#bookmark_tabs-about-the-application)
  * [Preview](#electric_plug-preview)

## :performing_arts: Introduction

It is the final project of **OOP course**, create all the logic referent to a Theater Ticket Dispenser.

The practice is composed of its **design** and its **implementation** (about the same topic but indepent).

The GUI was carried out by the teachers of the course.

### :rocket: Start-up the application

First, clone the repository and cd to the project.

```
git clone https://github.com/KandV008/Theater-Ticket-Dispenser.git
cd Theater-Ticket-Dispenser
```
Second, access to cd where is the main app application and run the project.
```
cd TheaterApp/src/theaterapp
javac TheaterApp.java
```


***

## :black_nib: Design

### :european_post_office: Class Diagrams

![main-diagram](./files-to-README/Diagrams/CD-main-class.svg)

 *Figure 1 - Main Diagram*

![sale-ticket-diagram](./files-to-README/Diagrams/CD-sale_ticket_operation.svg)

 *Figure 2 - Sale Ticket Diagram*

![relation-with-file-diagram](./files-to-README/Diagrams/CD-relation_with_file.svg)

*Figure 3 - Relation with File Diagram*

![relation-with-packages](./files-to-README/Diagrams/CD-relation_with_packages.svg)

*Figure 4 - Relation with Packages Diagram*

### :chart_with_upwards_trend: Sequence Diagrams

![program-starts-diagram](./files-to-README/Diagrams/SD-program_starts.svg)

*Figure 5 - Program Starts Diagram*

![ticket-sales-diagram](./files-to-README/Diagrams/SD-ticket_sales.svg)

*Figure 6 - Ticket Sales Diagram*

### :running: Activity Diagram

![text-traduction-diagram](./files-to-README/Diagrams/AD-text_traduction.png)

*Figure 7 - Text Traduction Diagram*

![sale-process-diagram](./files-to-README/Diagrams/AD-sale_process.svg)

*Figure 8 - Sale Process Diagram*

***

## :floppy_disk: Implementation

### :bookmark_tabs: About the application

It is a theater ticket dispenser software, where you can buy ticket for a perfomance, at the moment.

The application manages all the information about the theatre and their zones. 

Add the possibility to add more options, for example, buy binoculars or  librettos.

Allow to change the language to **spanish**, **basque**, **catalan**, **galician** or **english**.

#### :wrench: Technical Features

* JDK 8
* Oriented to Spaniards, Galicians, Basques, Catalans and English.

### :electric_plug: Preview

![index](./files-to-README/Screens/index.png)

*Figure 9 - Menu Screen*

![languageScreen](./files-to-README/Screens/languageScreen.png)

*Figure 10 - Language Selection Screen*

![dateScreen](./files-to-README/Screens/dateScreen.png)

*Figure 11 - Day Selection Screen*

![zoneScreen](./files-to-README/Screens/ZoneScreen.png)

*Figure 12 - Theater Area Selection Screen*

![seatScreen](./files-to-README/Screens/SeatScreen.png)

*Figure 13 - Theater Seats Selection Screen*

![paymentScreen](./files-to-README/Screens/PaymentScreen.png)

*Figure 14 - Payment Screen*

![ticketScreen](./files-to-README/Screens/TicketScreen.png)

*Figure 15 - Ticket Screen*
