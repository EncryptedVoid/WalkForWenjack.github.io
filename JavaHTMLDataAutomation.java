// All imports required for this build/project
import java.io.*;
import java.util.Scanner;
import java.lang.StringBuilder;

// Main class
class JavaHTMLDataAutomation {

    // Universal int var. for For loops
    public static int i = 0;

    // Class for organizing all essential process and dividing them into formatting processes or generic ones
    public static class Essentials {

        // Class for organizing all process involved in data formatting
        public static class Formatting {

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

        }

        // Class for organizing all process involved in actions
        public static class Processes {

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
                        array[txtFileLength] = Essentials.Formatting.Capitalization(scanner.nextLine());
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

                for(txtFileLength=0; txtFileLength<array.length;txtFileLength++){
                    array[txtFileLength] = unitFirst+ scanner.nextLine() +unitLast;
                }

                scanner.close();

                return array;

            }

            // Method for writing to any file and used to write to .html files and .txt files based on the spreadsheet
            public static void Writer(String material, String HTMLFileName) throws IOException {
                File thisFile = new File(HTMLFileName);
                BufferedWriter GoergeOrwell = new BufferedWriter(new FileWriter(thisFile));
                GoergeOrwell.write(material);
                GoergeOrwell.close();
            }

            // Method for formatting all arrays and strings into a singular output
            public static String HTML(int arrayLength, String [] category1Data, String [] category2Data, String [] category3Data,
                                      String [] category4Data, String [] category5Data, String HTMLStarter, String HTMLEnder) {

                StringBuilder HTMLcode = new StringBuilder();

                HTMLcode.append(HTMLStarter);

                HTMLcode.append("\t").append("<tbody>").append("\n")
                        .append("\t").append("\t").append("<tr>").append("\n");

                for(i = 0; i < arrayLength; i++) {

                    i++; String rank = Integer.toString(i); i--;

                    if(i == 0) {
                        HTMLcode.append("\t").append("\t").append("\t");
                    } else {
                        HTMLcode.append("\n").append("\t").append("\t").append("\t");
                    }

                    HTMLcode.append(Essentials.Formatting.DataToTableData("ranks", rank)).append("\n")
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

                HTMLcode.append(HTMLEnder);

                return HTMLcode.toString();

            }
        }

        // Void for holding all initiation of process into main(String [] args)
        public static void Initiate() throws IOException {

            Speech.start();

            Coding.WinnersByKms.classroom();
            Coding.WinnersByDonations.classroom();

            Coding.WinnersByKms.participants();
            Coding.WinnersByDonations.participant();

            Speech.end();

        }

    }

    // Class for organizing all data
    public static class DataPack {
        // Class for all Classroom data (CD) values
        public static class ClassData {

            // Setting file path String for any of the required files so as to shorten the String given as the argument
            public static final String txtFilePath =
                    "C:\\Users\\ashiq\\OneDrive\\Desktop\\Git\\WalkForWenjack.github.io\\txtFiles\\ClassroomData";

            // Setting arrayMax int for setting size for arrays & using Try/Catch if missing files
            public static int arrayMax(String winnerCategory) throws FileNotFoundException {
                return Essentials.Processes.ArrayMaxCalc(txtFilePath+winnerCategory+"classTeachers.txt");
            }

            static String [] array = null;

            public static String [] classCodes(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.TxtToArray(txtFilePath+winnerCategory+"classCodes.txt", arrayMax(winnerCategory), false);

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("classCode", array[i]);
                }

                return array;
            }
            public static String [] classGrades(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.FormattedTxtToArray(txtFilePath+winnerCategory+"classGrades.txt", arrayMax(winnerCategory), "Grade ", "");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("classGrade", array[i]);
                }

                return array;
            }
            public static String [] classDonations(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.FormattedTxtToArray(txtFilePath+winnerCategory+"classDonations.txt", arrayMax(winnerCategory), "$", "");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("classDonation", array[i]);
                }

                return array;
            }
            public static String [] classKms(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.FormattedTxtToArray(txtFilePath+winnerCategory+"classKms.txt", arrayMax(winnerCategory), "", "kms");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("classKms", array[i]);
                }

                return array;
            }
            public static String [] classTeachers(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.TxtToArray(txtFilePath+winnerCategory+"classTeachers.txt", arrayMax(winnerCategory), true);

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("classTeachers", array[i]);
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
                return Essentials.Processes.ArrayMaxCalc(txtFilePath+winnerCategory+"participantNames.txt");
            }

            public static String [] participantNames(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.TxtToArray(txtFilePath+winnerCategory+"participantNames.txt", arrayMax(winnerCategory), true);

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("participantName", array[i]);
                }

                return array;
            }
            public static String [] participantGrades(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.FormattedTxtToArray(txtFilePath+winnerCategory+"participantGrades.txt", arrayMax(winnerCategory), "Grade ", "");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("participantGrade", array[i]);
                }

                return array;
            }
            public static String [] participantDonations(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.FormattedTxtToArray(txtFilePath+winnerCategory+"participantDonations.txt", arrayMax(winnerCategory), "$", "");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("participantDonation", array[i]);
                }

                return array;
            }
            public static String [] participantKms(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.FormattedTxtToArray(txtFilePath+winnerCategory+"participantKms.txt", arrayMax(winnerCategory), "", "kms");

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("participantKms", array[i]);
                }

                return array;
            }
            public static String [] participantTeachers(String winnerCategory) throws FileNotFoundException {
                array = Essentials.Processes.TxtToArray(txtFilePath+winnerCategory+"participantTeachers.txt", arrayMax(winnerCategory), true);

                for(i = 0; i < arrayMax(winnerCategory); i++) {
                    array[i] = Essentials.Formatting.DataToTableData("participantTeacher", array[i]);
                }

                return array;
            }
        }
    }

    // Class for storing and "compressing"/organizing all HTML code constant for HTML pages
    public static final class HTMLConstants {
        public static String Time() {
            return "Last Updated: " + java.util.Calendar.getInstance().getTime();
        }

        public static final String ParticipantKmsStart = """
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
                                """+"<tr><td id=\"localUpdateDate\"><i>"+Time()+"</i></td></tr>"+"""
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>

                        <div id="buttonDiv">
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
                                        <th class="leftText" id="homeroom">Homeroom:</th>
                                        <th class="leftText" id="name">Participant Name:</th>
                                        <th class="rightText" id="grade">Grade</th>
                                        <th class="rightText" id="kms">Total Km(s)</th>
                                        <th class="rightText" id="donation">$ raised:</th>
                                    </tr>
                                </thead>
                
                """;

        public static final String ParticipantDonationsStart = """
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
                                """+"<tr><td id=\"localUpdateDate\"><i>"+Time()+"</i></td></tr>"+"""
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>
                                
                        <div id="buttonDiv">
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
                                        <th class="leftText" id="homeroom">Homeroom:</th>
                                        <th class="leftText" id="name">Participant Name:</th>
                                        <th class="rightText" id="grade">Grade</th>
                                        <th class="rightText" id="kms">Total Km(s)</th>
                                        <th class="rightText" id="donation">$ raised:</th>
                                    </tr>
                                </thead>
                                
                """;

        public static final String ClassKmsStart = """
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
                                """+"<tr><td id=\"localUpdateDate\"><i>"+Time()+"</i></td></tr>"+"""                                
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>

                        <div id="buttonDiv">
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
                                        <th class="leftText" id="homeroom">Homeroom:</th>
                                        <th class="leftText" id="name">Participant Name:</th>
                                        <th class="rightText" id="grade">Grade</th>
                                        <th class="rightText" id="kms">Total Km(s)</th>
                                        <th class="rightText" id="donation">$ raised:</th>
                                    </tr>
                                </thead>
                                
                """;

        public static final String ClassDonationsStart = """
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
                                """+"<tr><td id=\"localUpdateDate\"><i>"+Time()+"</i></td></tr>"+"""                                
                                <tr><td id="localUpdateDate"><i>PLEASE LET ME KNOW IF YOUR INFORMATION IS INCORRECT OR MISSING AT AGAZI2@OCDSB.CA</i></td></tr>
                            </table>
                        </div>
                                
                        <div id="buttonDiv">
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
                                        <th class="leftText" id="homeroom">Homeroom:</th>
                                        <th class="leftText" id="name">Participant Name:</th>
                                        <th class="rightText" id="grade">Grade</th>
                                        <th class="rightText" id="kms">Total Km(s)</th>
                                        <th class="rightText" id="donation">$ raised:</th>
                                    </tr>
                                </thead>
                                
                """;

        public static final String End = """
                </table>
                        </div>
                                
                        <div class="tableDiv" id="codingInformation">
                            <table><tr><td id="codeInfo"><i>Leaderboard coded with HTML, CSS, and Java by Ashiq Gazi</i></td></tr></table>
                        </div>
                    </body>
                </html>
        """;
    }

    // Class for organizing all process for Reading txt files & Writing to HTML files
    public static class Coding {

        public static String finalCode;

        // Setting file path String for any of the required files so as to shorten the String given as the argument
        public static final String HTMLFilePath =
                "C:\\Users\\ashiq\\OneDrive\\Desktop\\Git\\WalkForWenjack.github.io\\HTMLFiles";

        public static class WinnersByKms {

            public static String winnerCategory = "\\WinnersByKms\\"; // Winners for Kms travelled

            public static void classroom() throws IOException {
                finalCode = Essentials.Processes.HTML(DataPack.ClassData.arrayMax(winnerCategory), DataPack.ClassData.classTeachers(winnerCategory),
                        DataPack.ClassData.classDonations(winnerCategory), DataPack.ClassData.classCodes(winnerCategory), DataPack.ClassData.classGrades(winnerCategory),
                        DataPack.ClassData.classKms(winnerCategory), HTMLConstants.ClassKmsStart, HTMLConstants.End);
                Essentials.Processes.Writer(finalCode, HTMLFilePath+"classroomKms.html");
                System.out.println("classroomKms"+winnerCategory+".html in WalkForWenjack.project competed\n");
            }
            public static void participants() throws IOException {
                finalCode = Essentials.Processes.HTML(DataPack.ParticipantData.arrayMax(winnerCategory), DataPack.ParticipantData.participantTeachers(winnerCategory),
                        DataPack.ParticipantData.participantDonations(winnerCategory), DataPack.ParticipantData.participantNames(winnerCategory),
                        DataPack.ParticipantData.participantGrades(winnerCategory), DataPack.ParticipantData.participantKms(winnerCategory),
                        HTMLConstants.ParticipantKmsStart, HTMLConstants.End);
                Essentials.Processes.Writer(finalCode, HTMLFilePath+"participantKms.html");
                System.out.println("participantKms"+winnerCategory+".html in WalkForWenjack.project competed\n");
            }
        }
        public static class WinnersByDonations{

            public static String  winnerCategory = "\\WinnersByDonations\\"; // Winners for Donations raised

            public static void classroom() throws IOException {
                finalCode = Essentials.Processes.HTML(DataPack.ClassData.arrayMax(winnerCategory), DataPack.ClassData.classTeachers(winnerCategory),
                        DataPack.ClassData.classDonations(winnerCategory), DataPack.ClassData.classCodes(winnerCategory), DataPack.ClassData.classGrades(winnerCategory),
                        DataPack.ClassData.classKms(winnerCategory), HTMLConstants.ClassDonationsStart, HTMLConstants.End);
                Essentials.Processes.Writer(finalCode, HTMLFilePath+"classroomDonations.html");
                System.out.println("classroomDonations"+winnerCategory+".html in WalkForWenjack.project competed\n");
            }
            public static void participant() throws IOException {
                finalCode = Essentials.Processes.HTML(DataPack.ParticipantData.arrayMax(winnerCategory), DataPack.ParticipantData.participantTeachers(winnerCategory),
                        DataPack.ParticipantData.participantDonations(winnerCategory), DataPack.ParticipantData.participantNames(winnerCategory),
                        DataPack.ParticipantData.participantGrades(winnerCategory), DataPack.ParticipantData.participantKms(winnerCategory),
                        HTMLConstants.ParticipantDonationsStart, HTMLConstants.End);
                Essentials.Processes.Writer(finalCode, HTMLFilePath+"participantDonations.html");
                System.out.println("participantDonations"+winnerCategory+".html in WalkForWenjack.project competed\n");
            }

        }
    }

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

    // public static void main(String [] args)? If(you.dontKnow(this)) {you.dontknow(Java);}
    public static void main(String [] args) throws IOException { Essentials.Initiate(); }
}