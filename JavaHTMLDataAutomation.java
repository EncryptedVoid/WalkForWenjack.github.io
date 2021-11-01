// All imports required for this build/project
import java.io.*;
import java.util.Scanner;
import java.lang.StringBuilder;

// Main class
class JavaHTMLDataAutomation {

    // Universal int var. for For loops
    public static int i = 0;

    // Class for organizing all speech that should be outputted in console during compilation till the end
    public static class Speech {
        public static void start() {
            System.out.println("Initiating WalkForWenjack.project...\n");
            System.out.println("Retrieving all assets for WalkForWenjack.project...\n");
            System.out.println("Beginning system process for WalkForWenjack.project...\n");
        }
        public static void end() {
            System.out.println("WalkForWenjack.project completed\n");
            System.out.println("Do not forget to push all files onto Git Repository to update Github Pages\n");
            System.out.println("Have a great day\n");
        }
    }

    // Class for organizing all essential process and dividing them into formatting processes or generic ones
    public static class Vitals {

        // Class for organizing all process involved in data formatting
        public static class DataConfiguration {

            // Method for properly capitalizing names
            public static String Capitalization(String name) {
                StringBuilder capitalizedString = new StringBuilder();

                name = name + " "; // Added space for negating error

                for(i = 0; i < name.length()-1; i++) { // For loop to capitalize name in NameArray
                    if(i == 0) {
                        capitalizedString.append((name.charAt(i)+"").toUpperCase());
                    } else if((name.charAt(i)+"").equals("-") || (name.charAt(i)+"").equals(" ")) {
                        capitalizedString.append(name.charAt(i));
                        i++;
                        capitalizedString.append((name.charAt(i)+"").toUpperCase());

                        // If at space or hyphen, one can assume next letter must be capitalized
                    } else {
                        capitalizedString.append((name.charAt(i)+"").toLowerCase());
                    }
                }
                return capitalizedString.toString();
            }

            // Method for formatting individual table data
            public static String DataToTableData(String dataName, String arrayData) {
                return "<td id="+dataName+">"+arrayData+"</td>";
            }

            // For rounding numbers to the nearest decimal place given
            public static int Round(int preferredDecimalPlace, int dataValue) {
                return Math.round((dataValue*preferredDecimalPlace))/preferredDecimalPlace;
            }

            // For converting array data values to averaged data values
            public static String ClassAverageCalc(int dataCensusSize, int preferredDecimalPlace, int dataValue) {
                return Integer.toString(Round(preferredDecimalPlace, (dataValue/dataCensusSize)));
            }

        }

        // Class for organizing all process involved in actions
        public static class Operations {

            // How long the Arrays should be based on the length of the English.txt File:
            public static int ArrayMaxCalc(String fileName) throws FileNotFoundException {

                int fileLength = 0;
                File file = new File(fileName);
                Scanner scanner = new Scanner(file);

                while(scanner.hasNextLine()) {
                    scanner.nextLine();
                    fileLength++;
                }

                scanner.close();
                return fileLength;

            }

            // Method to initialize all .txt files into Arrays:
            public static String[] TxtToArray(String fileName, int txtFileLength, boolean isName) throws FileNotFoundException {

                File file = new File(fileName);
                Scanner scanner = new Scanner(file);
                String [] array = new String[txtFileLength];

                for(txtFileLength=0; txtFileLength<array.length;txtFileLength++){
                    if(isName){
                        array[txtFileLength] = DataConfiguration.Capitalization(scanner.nextLine());
                    } else {
                        array[txtFileLength] = scanner.nextLine();
                    }
                }

                scanner.close();

                return array;

            }

            // Method to initialize all .txt files into Arrays:
            public static String[] FormattedTxtToArray(String fileName, int txtFileLength, String unitFirst, String unitLast) throws FileNotFoundException {

                File file = new File(fileName);
                Scanner scanner = new Scanner(file);
                String [] array = new String[txtFileLength];

                for(i=0; i<txtFileLength;i++){
                    if(i++ != txtFileLength-1) {
                        if(scanner.nextLine().equals("CW Staff")) {
                            unitFirst = ""; unitLast = "";
                        }
                        array[i] = unitFirst+scanner.nextLine()+unitLast;
                    }
                }

                scanner.close();

                return array;

            }

            // Method for writing to any file and used to write to .html files and .txt files based on the spreadsheet
            public static void Writer(String material, String HTMLFileName) throws IOException {
                File thisFile = new File(HTMLFileName);
                BufferedWriter Orwell = new BufferedWriter(new FileWriter(thisFile));
                Orwell.write(material);
                Orwell.close();
            }

            // Method for formatting all arrays and strings into a singular output
            public static String HTML(int arrayLength, String [] category1Data, String [] category2Data, String [] category3Data,
                                      String [] category4Data, String [] category5Data, String HTMLHeader, String HTMLFooter) {

                StringBuilder HTMLcode = new StringBuilder();

                HTMLcode.append(HTMLHeader);

                HTMLcode.append("\t").append("<tbody>").append("\n")
                        .append("\t").append("\t").append("<tr>").append("\n");

                for(i = 0; i < arrayLength; i++) {

                    i++; String rank = Integer.toString(i); i--;

                    if(i == 0) {
                        HTMLcode.append("\t").append("\t").append("\t");
                    } else {
                        HTMLcode.append("\n").append("\t").append("\t").append("\t");
                    }

                    HTMLcode.append(DataConfiguration.DataToTableData("ranks", rank)).append("\n")
                            .append("\t").append("\t").append("\t").append(category1Data[i]).append("\n")
                            .append("\t").append("\t").append("\t").append(category3Data[i]).append("\n")
                            .append("\t").append("\t").append("\t").append(category4Data[i]).append("\n")
                            .append("\t").append("\t").append("\t").append(category5Data[i]).append("\n")
                            .append("\t").append("\t").append("\t").append(category2Data[i]).append("\n")
                            .append("\t").append("\t").append("</tr>").append("\n");

                    // For starting a new row in the HTML table
                    if(i == arrayLength-1) {
                        HTMLcode.append("");
                    } else {
                        HTMLcode.append("\t").append("\t").append("<tr>");
                    }

                }

                HTMLcode.append("\t").append("</tbody>");

                HTMLcode.append(HTMLFooter);

                return HTMLcode.toString();

            }

        }

        // Void for holding all initiation of process into main(String [] args)
        public static void Execute() throws IOException {

            // For adding dialogue to the console
            Speech.start();

            // For completing/Updating the index.html data statuses
            Coding.Winners();

            // For completing/Updating the classroom data statuses
            Coding.WinnersByKms.classroom();
            Coding.WinnersByDonations.classroom();

            // For completing/Updating the participant data statuses
            Coding.WinnersByKms.participants();
            Coding.WinnersByDonations.participant();

            // For adding dialogue to the console
            Speech.end();

        }

    }

    // Class for storing and "compressing"/organizing all HTML code constant for HTML pages
    public static final class HTMLConstants {
        public static String Time() {
            return "" + java.util.Calendar.getInstance().getTime() + "";
        }

        public static final String WinnerIndexHeader = """
                <!DOCTYPE html>

                <html lang="en">
                    <head>
                        <title>CW's Walk For Wenjack Leaderboard</title>
                        <meta charset="UTF-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="styling.css">
                        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
                        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                        <link rel="stylesheet" href="https://fonts.gstatic.com/s/productsans/v5/HYvgU2fE2nRJvZ5JFAumwegdm0LZdjqr5-oayXSOefg.woff2">
                    </head>

                    <body>

                        <div class="tableDiv" id="localUpdateDateDiv">
                            <table>
                                """+"<tr><td id=\"localUpdateDate\"><i>Last Updated: "+Time()+"</i></td></tr>"+"""
                                
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>

                        <div id="buttonDiv">
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="index.html">WINNER LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="HTMLFilesparticipantKms.html">PARTICIPANT LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="classroomLeaderboard"><a href="HTMLFilesclassroomKms.html">CLASSROOM LEADERBOARD</a></button>
                        </div>

                        <div class="tableDiv" id="leaderboard">
                            <header>
                                <h1>CW's Walk For Wenjack Leaderboard - Participants</h1>
                            </header>

                            <table id="datatable">
                                <thead>
                                    <tr>
                                        <th id="rank"></th>
                                        <th id="homeroom">Teacher/ClassCode:</th>
                                        <th id="name">Name:</th>
                                        <th id="grade">Grade:</th>
                                        <th id="kms">Kilometres(s):</th>
                                        <th id="donation">Donations:</th>
                                    </tr>
                                </thead>
                
                """;

        public static final String ParticipantKmsHeader = """
                <!DOCTYPE html>

                <html lang="en">
                    <head>
                        <title>CW's Walk For Wenjack Leaderboard</title>
                        <meta charset="UTF-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="styling.css">
                        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
                        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                        <link rel="stylesheet" href="https://fonts.gstatic.com/s/productsans/v5/HYvgU2fE2nRJvZ5JFAumwegdm0LZdjqr5-oayXSOefg.woff2">
                    </head>

                    <body>

                        <div class="tableDiv" id="localUpdateDateDiv">
                            <table>
                                """+"<tr><td id=\"localUpdateDate\"><i>Last Updated: "+Time()+"</i></td></tr>"+"""
                                
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>

                        <div id="buttonDiv">
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="index.html">WINNER LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="HTMLFilesparticipantKms.html">PARTICIPANT LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="classroomLeaderboard"><a href="HTMLFilesclassroomKms.html">CLASSROOM LEADERBOARD</a></button>
                        </div>

                        <div class="tableDiv" id="leaderboard">
                            <header>
                                <h1>CW's Walk For Wenjack Leaderboard - Participants</h1>
                                <nav>
                                    <a href="HTMLFilesparticipantKms.html" class="active" id="CW">Kilometres</a>
                                    <a href="HTMLFilesparticipantDonations.html" class="" id="CW">Donations</a>
                                </nav>
                            </header>

                            <table id="datatable">
                                <thead>
                                    <tr>
                                        <th id="rank"></th>
                                        <th id="homeroom">Homeroom:</th>
                                        <th id="name">Participant Name:</th>
                                        <th id="grade">Grade</th>
                                        <th id="kms">Total Km(s)</th>
                                        <th id="donation">$ raised:</th>
                                    </tr>
                                </thead>
                
                """;

        public static final String ParticipantDonationsHeader = """
                <!DOCTYPE html>
                                
                <html lang="en">
                    <head>
                        <title>CW's Walk For Wenjack Leaderboard</title>
                        <meta charset="UTF-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="styling.css">
                        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
                        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                        <link rel="stylesheet" href="https://fonts.gstatic.com/s/productsans/v5/HYvgU2fE2nRJvZ5JFAumwegdm0LZdjqr5-oayXSOefg.woff2">
                    </head>
                                
                    <body>
                                
                        <div class="tableDiv" id="localUpdateDateDiv">
                            <table>
                                """+"<tr><td id=\"localUpdateDate\"><i>Last Updated: "+Time()+"</i></td></tr>"+"""
                                
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>
                                
                        <div id="buttonDiv">
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="index.html">WINNER LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="HTMLFilesparticipantKms.html">PARTICIPANT LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="classroomLeaderboard"><a href="HTMLFilesclassroomKms.html">CLASSROOM LEADERBOARD</a></button>
                        </div>
                                
                        <div class="tableDiv" id="leaderboard">
                            <header>
                                <h1>CW's Walk For Wenjack Leaderboard - Participants</h1>
                                <nav>
                                    <a href="HTMLFilesparticipantKms.html" class="" id="CW">Kilometres</a>
                                    <a href="HTMLFilesparticipantDonations.html" class="active" id="CW">Donations</a>
                                </nav>
                            </header>
                                
                            <table id="datatable">
                                <thead>
                                    <tr>
                                        <th id="rank"></th>
                                        <th id="homeroom">Homeroom:</th>
                                        <th id="name">Participant Name:</th>
                                        <th id="grade">Grade</th>
                                        <th id="kms">Total Km(s)</th>
                                        <th id="donation">$ raised:</th>
                                    </tr>
                                </thead>
                                
                """;

        public static final String ClassKmsHeader = """
                <!DOCTYPE html>

                <html lang="en">
                    <head>
                        <title>CW's Walk For Wenjack Leaderboard</title>
                        <meta charset="UTF-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="styling.css">
                        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
                        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                        <link rel="stylesheet" href="https://fonts.gstatic.com/s/productsans/v5/HYvgU2fE2nRJvZ5JFAumwegdm0LZdjqr5-oayXSOefg.woff2">
                    </head>

                    <body>

                        <div class="tableDiv" id="localUpdateDateDiv">
                            <table>
                                """+"<tr><td id=\"localUpdateDate\"><i>Last Updated: "+Time()+"</i></td></tr>"+"""    
                                                            
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>

                        <div id="buttonDiv">
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="index.html">WINNER LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="HTMLFilesparticipantKms.html">PARTICIPANT LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="classroomLeaderboard"><a href="HTMLFilesclassroomKms.html">CLASSROOM LEADERBOARD</a></button>
                        </div>

                        <div class="tableDiv" id="leaderboard">
                            <header>
                                <h1>CW's Walk For Wenjack Leaderboard - Classrooms</h1>
                                <nav>
                                    <a href="HTMLFilesparticipantKms.html" class="active" id="CW">Kilometres</a>
                                    <a href="HTMLFilesparticipantDonations.html" class="" id="CW">Donations</a>
                                </nav>
                            </header>

                            <table id="datatable">
                                <thead>
                                    <tr>
                                        <th id="rank"></th>
                                        <th id="homeroom">Class Code:</th>
                                        <th id="name">Class Teacher:</th>
                                        <th id="grade">Class Size:</th>
                                        <th id="kms">Average Kms:</th>
                                        <th id="donation">Average Donations:</th>
                                    </tr>
                                </thead>
                                
                """;

        public static final String ClassDonationsHeader = """
                <!DOCTYPE html>
                                
                <html lang="en">
                    <head>
                        <title>CW's Walk For Wenjack Leaderboard</title>
                        <meta charset="UTF-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="styling.css">
                        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
                        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                        <link rel="stylesheet" href="https://fonts.gstatic.com/s/productsans/v5/HYvgU2fE2nRJvZ5JFAumwegdm0LZdjqr5-oayXSOefg.woff2">
                    </head>
                                
                    <body>
                                
                        <div class="tableDiv" id="localUpdateDateDiv">
                            <table>
                                """+"<tr><td id=\"localUpdateDate\"><i>Last Updated: "+Time()+"</i></td></tr>"+"""  
                                                              
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>
                                
                        <div id="buttonDiv">
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="index.html">WINNER LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="participantButton"><a href="HTMLFilesparticipantKms.html">PARTICIPANT LEADERBOARD</a></button>
                            <button class="glow-on-hover" type="button" id="classroomLeaderboard"><a href="HTMLFilesclassroomKms.html">CLASSROOM LEADERBOARD</a></button>
                        </div>
                                
                        <div class="tableDiv" id="leaderboard">
                            <header>
                                <h1>CW's Walk For Wenjack Leaderboard - Classrooms</h1>
                                <nav>
                                    <a href="HTMLFilesparticipantKms.html" class="" id="CW">Kilometres</a>
                                    <a href="HTMLFilesparticipantDonations.html" class="active" id="CW">Donations</a>
                                </nav>
                            </header>
                                
                            <table id="datatable">
                                <thead>
                                    <tr>
                                        <th id="rank"></th>
                                        <th id="homeroom">Class Code:</th>
                                        <th id="name">Class Teacher:</th>
                                        <th id="grade">Class Size:</th>
                                        <th id="kms">Average Kms:</th>
                                        <th id="donation">Average Donations:</th>
                                    </tr>
                                </thead>
                                
                """;

        public static final String DefaultFooter = """
                </table>
                        </div>
                                
                        <div class="tableDiv" id="codingInformation">
                            <table><tr><td id="codeInfo"><i>Leaderboard coded with HTML, CSS, and Java by Ashiq Gazi</i></td></tr></table>
                        </div>
                    </body>
                </html>
        """;
    }

    // Class for organizing all data
    public static class DataPackages {
        // Class for all Classroom data (CD) values
        public static class ClassData {

            // Setting file path String for any of the required files so as to shorten the String given as the argument
            public static final String txtFilePath =
                    "C:\\Users\\ashiq\\OneDrive\\Desktop\\Git\\WalkForWenjack.github.io\\txtFiles\\ClassroomData";

            // Setting arrayMax int for setting size for arrays & using Try/Catch if missing files
            public static int arrayMax(String winnerCategory) throws FileNotFoundException {
                return Vitals.Operations.ArrayMaxCalc(txtFilePath+winnerCategory+"classTeachers.txt");
            }

            static String [] array = null;

            public static String [] classCodes(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.TxtToArray(txtFilePath+winnerCategory+"classCodes.txt", arrayMax(winnerCategory), false);

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("classCode", array[i]);
                }

                return array;
            }

            public static String [] totalStudents(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+winnerCategory+"totalStudents.txt", arrayMax(winnerCategory), "Grade ", "");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("classSize", array[i]);
                }

                return array;
            }

            public static String [] classDonations(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+winnerCategory+"classDonations.txt", arrayMax(winnerCategory), "$", "");

                // ClassAverageCalc(int classSize, byte preferredDecimalPlace, int dataValue)
                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("classDonationAvg", array[i]);
                }

                return array;
            }

            public static String [] classKms(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+winnerCategory+"classKms.txt", arrayMax(winnerCategory), "", "kms");

                // ClassAverageCalc(int classSize, byte preferredDecimalPlace, int dataValue)
                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("classKmsAvg", array[i]);
                }

                return array;
            }

            public static String [] classTeachers(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.TxtToArray(txtFilePath+winnerCategory+"classTeachers.txt", arrayMax(winnerCategory), true);

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("classTeachers", array[i]);
                }

                return array;
            }

        }

        // Class for all Participant data (PD) values
        public static class ParticipantData {

            // Setting file path String for any of the required files so as to shorten the String given as the argument
            public static final String txtFilePath =
                    "C:\\Users\\ashiq\\OneDrive\\Desktop\\Git\\WalkForWenjack.github.io\\txtFiles\\ParticipantData";

            static String [] array = null;

            public static int arrayMax(String winnerCategory) throws FileNotFoundException {
                return Vitals.Operations.ArrayMaxCalc(txtFilePath+winnerCategory+"participantNames.txt");
            }

            public static String [] participantNames(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.TxtToArray(txtFilePath+winnerCategory+"participantNames.txt", arrayMax(winnerCategory), true);

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("participantName", array[i]);
                }

                return array;
            }

            public static String [] participantGrades(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+winnerCategory+"participantGrades.txt", arrayMax(winnerCategory), "Grade ", "");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("participantGrade", array[i]);
                }

                return array;
            }

            public static String [] participantDonations(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+winnerCategory+"participantDonations.txt", arrayMax(winnerCategory), "$", "");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("participantDonation", array[i]);
                }

                return array;
            }

            public static String [] participantKms(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+winnerCategory+"participantKms.txt", arrayMax(winnerCategory), "", "kms");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("participantKms", array[i]);
                }

                return array;
            }

            public static String [] participantTeachers(String winnerCategory) throws FileNotFoundException {
                array = Vitals.Operations.TxtToArray(txtFilePath+winnerCategory+"participantTeachers.txt", arrayMax(winnerCategory), true);

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("participantTeacher", array[i]);
                }

                return array;
            }

        }

        // Class for all winner data (WD) values
        public static class WinnerData {

            // Setting file path String for any of the required files so as to shorten the String given as the argument
            public static final String txtFilePath =
                    "C:\\Users\\ashiq\\OneDrive\\Desktop\\Git\\WalkForWenjack.github.io\\txtFiles\\WinnerData\\";

            static String [] array = null;

            public static int arrayMax() throws FileNotFoundException {
                return Vitals.Operations.ArrayMaxCalc(txtFilePath+"winnerNames.txt");
            }

            public static String [] winnerNames() throws FileNotFoundException {
                array = Vitals.Operations.TxtToArray(txtFilePath+"winnerNames.txt", arrayMax(), true);

                for(i = 0; i < arrayMax(); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("winnerName", array[i]);
                }

                return array;
            }

            public static String [] winnerLevel() throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+"winnerLevel.txt", arrayMax(), "Grade ", "");

                for(i = 0; i < arrayMax(); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("winnerLevel", array[i]);
                }

                return array;
            }

            public static String [] winnerDonations() throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+"winnerDonations.txt", arrayMax(), "$", "");

                for(i = 0; i < arrayMax(); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("winnerDonation", array[i]);
                }

                return array;
            }

            public static String [] winnerKms() throws FileNotFoundException {
                array = Vitals.Operations.FormattedTxtToArray(txtFilePath+"winnerKms.txt", arrayMax(), "", "kms");

                for(i = 0; i < arrayMax(); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("winnerKms", array[i]);
                }

                return array;
            }

            public static String [] winnerTeachers() throws FileNotFoundException {
                array = Vitals.Operations.TxtToArray(txtFilePath+"winnerTeachers.txt", arrayMax(), true);

                for(i = 0; i < arrayMax(); i++) {
                    array[i] = Vitals.DataConfiguration.DataToTableData("winnerTeacher", array[i]);
                }

                return array;
            }

        }
    }

    // Class for organizing all process for Reading txt files & Writing to HTML files
    public static class Coding {

        public static String finalCode;

        // Setting file path String for any of the required files so as to shorten the String given as the argument
        public static final String HTMLFilePath =
                "C:\\Users\\ashiq\\OneDrive\\Desktop\\Git\\WalkForWenjack.github.io\\HTMLFiles";

        // class for organizing all processes required for any Winners by Kms files/data
        public static class WinnersByKms {

            public static String winnerCategory = "\\WinnersByKms\\"; // Winners for Kms travelled

            public static void classroom() throws IOException {
                finalCode = Vitals.Operations.HTML(DataPackages.ClassData.arrayMax(winnerCategory), DataPackages.ClassData.classTeachers(winnerCategory),
                        DataPackages.ClassData.classDonations(winnerCategory), DataPackages.ClassData.classCodes(winnerCategory), DataPackages.ClassData.totalStudents(winnerCategory),
                        DataPackages.ClassData.classKms(winnerCategory), HTMLConstants.ClassKmsHeader, HTMLConstants.DefaultFooter);
                Vitals.Operations.Writer(finalCode, HTMLFilePath+"classroomKms.html");
                System.out.println("classroomKms"+winnerCategory+".html in WalkForWenjack.project competed\n");
            }
            public static void participants() throws IOException {
                finalCode = Vitals.Operations.HTML(DataPackages.ParticipantData.arrayMax(winnerCategory), DataPackages.ParticipantData.participantTeachers(winnerCategory),
                        DataPackages.ParticipantData.participantDonations(winnerCategory), DataPackages.ParticipantData.participantNames(winnerCategory),
                        DataPackages.ParticipantData.participantGrades(winnerCategory), DataPackages.ParticipantData.participantKms(winnerCategory),
                        HTMLConstants.ParticipantKmsHeader, HTMLConstants.DefaultFooter);
                Vitals.Operations.Writer(finalCode, HTMLFilePath+"participantKms.html");
                System.out.println("participantKms"+winnerCategory+".html in WalkForWenjack.project competed\n");
            }
        }

        // class for organizing all processes required for any Winners by Donations files/data
        public static class WinnersByDonations{

            public static String winnerCategory = "\\WinnersByDonations\\"; // Winners for Donations raised

            public static void classroom() throws IOException {
                finalCode = Vitals.Operations.HTML(DataPackages.ClassData.arrayMax(winnerCategory), DataPackages.ClassData.classTeachers(winnerCategory),
                        DataPackages.ClassData.classDonations(winnerCategory), DataPackages.ClassData.classCodes(winnerCategory), DataPackages.ClassData.totalStudents(winnerCategory),
                        DataPackages.ClassData.classKms(winnerCategory), HTMLConstants.ClassDonationsHeader, HTMLConstants.DefaultFooter);
                Vitals.Operations.Writer(finalCode, HTMLFilePath+"classroomDonations.html");
                System.out.println("classroomDonations"+winnerCategory+".html in WalkForWenjack.project competed\n");
            }
            public static void participant() throws IOException {
                finalCode = Vitals.Operations.HTML(DataPackages.ParticipantData.arrayMax(winnerCategory), DataPackages.ParticipantData.participantTeachers(winnerCategory),
                        DataPackages.ParticipantData.participantDonations(winnerCategory), DataPackages.ParticipantData.participantNames(winnerCategory),
                        DataPackages.ParticipantData.participantGrades(winnerCategory), DataPackages.ParticipantData.participantKms(winnerCategory),
                        HTMLConstants.ParticipantDonationsHeader, HTMLConstants.DefaultFooter);
                Vitals.Operations.Writer(finalCode, HTMLFilePath+"participantDonations.html");
                System.out.println("participantDonations"+winnerCategory+".html in WalkForWenjack.project competed\n");
            }

        }

        public static void Winners() throws IOException {

            finalCode = Vitals.Operations.HTML(DataPackages.WinnerData.arrayMax(), DataPackages.WinnerData.winnerTeachers(),
                    DataPackages.WinnerData.winnerDonations(), DataPackages.WinnerData.winnerLevel(), DataPackages.WinnerData.winnerNames(),
                    DataPackages.WinnerData.winnerKms(), HTMLConstants.WinnerIndexHeader, HTMLConstants.DefaultFooter);
            Vitals.Operations.Writer(finalCode, HTMLFilePath+"index.html");
            System.out.println("index.html in WalkForWenjack.project competed\n");

        }
    }

    // public static void main(String [] args)? If(you.dontKnow(this)) {you.dontknow(Java);}
    public static void main(String [] args) throws IOException { Vitals.Execute(); }
}