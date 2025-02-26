# Walk For Wenjack Leaderboard

![Leaderboard Preview](https://via.placeholder.com/800x400?text=Wenjack+Leaderboard)

## Project Overview

This is a custom-built leaderboard system developed for Chantry Westmount's "Walk For Wenjack" charity event, a fundraiser honoring the memory of Chanie Wenjack and supporting Indigenous reconciliation efforts. The leaderboard tracks both kilometers walked and donations raised by participants and classrooms.

## Features

- **Multiple Leaderboard Views**:
  - Winner Leaderboard - Showcasing top performers overall
  - Participant Leaderboard - Individual ranking by kilometers or donations
  - Classroom Leaderboard - Class ranking by kilometers or donations

- **Sorting Options**:
  - Sort by kilometers walked
  - Sort by donations raised

- **Responsive Design**:
  - Clean, modern interface
  - Gradient background with glassmorphic elements
  - Mobile-friendly layout

- **Automated Data Processing**:
  - Java backend for processing spreadsheet data
  - Automatic HTML generation
  - Efficient data organization and sorting

## Technical Implementation

This project was built using:

- **HTML5** - Structured content and tables
- **CSS3** - Modern styling with gradients and responsive design
- **Java** - Backend data processing and HTML generation
- **GitHub Pages** - Web hosting

The automated data processing system was built entirely from scratch without any web frameworks or JavaScript libraries, making it an impressive achievement for a Grade 10 self-taught developer.

## Architecture

The project follows a unique architecture where Java handles all data manipulation:

1. **Data Source**: Raw data stored in text files (potentially exported from spreadsheets)
2. **Java Processing**: 
   - `JavaHTMLDataAutomation.java` reads data from text files
   - Sorts and organizes information
   - Generates complete HTML files
3. **Static Web Pages**: 
   - The generated HTML/CSS is hosted on GitHub Pages
   - No client-side JavaScript required
   - Fast loading and rendering

## Key Components

- **HTML Files**:
  - `index.html` - Main winner leaderboard
  - `HTMLFilesparticipantKms.html` - Participants sorted by kilometers
  - `HTMLFilesparticipantDonations.html` - Participants sorted by donations
  - `HTMLFilesclassroomKms.html` - Classrooms sorted by kilometers
  - `HTMLFilesclassroomDonations.html` - Classrooms sorted by donations

- **Java Components**:
  - `JavaHTMLDataAutomation.java` - Core processing engine
  - `Vitals` class - Core methods for data handling
  - `DataPackages` class - Data structure organization
  - `Coding` class - HTML generation logic
  - `HTMLConstants` class - HTML templates

- **CSS**:
  - `styling.css` - Custom styling with modern design elements

## Notable Technical Achievements

- **Custom Data Formatting**: Comprehensive system to format, sort, and present data
- **Automation**: Complete automation of HTML generation from raw data
- **Java-Based Web Solution**: Innovative use of Java for web content generation
- **No Front-End Frameworks**: Built without reliance on JavaScript frameworks or libraries
- **Data Processing**: Advanced data handling capabilities through custom Java implementation

## Future Improvements

While the current implementation effectively meets the project requirements, potential future enhancements could include:

- Database integration for real-time updates
- User authentication for direct data submission
- Admin dashboard for event organizers
- Interactive charts and statistics
- API development for mobile app integration
- Automated email reports to participants

## Project Background

This project was independently developed by Ashiq Gazi as a Grade 10 student, entirely self-taught. It demonstrates strong programming fundamentals, problem-solving skills, and the ability to create practical solutions with limited resources and prior knowledge.

The Walk For Wenjack event commemorates Chanie Wenjack, whose story represents the experiences of Indigenous children in residential schools and supports reconciliation initiatives in Canadian education.

## Acknowledgments

- Teachers and students at Chantry Westmount for participating in the Walk For Wenjack event
- All participants who contributed kilometers and donations to this important cause
- Special thanks to those who provided feedback and support during development