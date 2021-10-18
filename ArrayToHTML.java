import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.lang.Math;

class ArrayToHTML {

    public static int i = 0;
    public static final String basicFilePath = "C:\\Users\\ashiq\\OneDrive\\Desktop\\Git\\WalkForWenjack.github.io\\txtFiles\\";
    public static final String namesTxt = basicFilePath+"Names.txt",
                               gradesTxt = basicFilePath+"Grades.txt",
                               kilometresTxt = basicFilePath+"Kilometres.txt",
                               donationsTxt = basicFilePath+"MoneyRaised.txt",
                               homeroomTxt = basicFilePath+"Homeroom.txt";

    // How long the Arrays should be based on the length of the English.txt File:
    public static int ArrayMax(String fileName) throws FileNotFoundException {

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

    // Method for properly capitalizing names
    public static String formattedName(String name) {
        StringBuilder formattedName = new StringBuilder();

        for(i = 0; i < name.length(); i++) {
            if(i == 0) {
                formattedName.append(name.charAt(i));
            } else if((name.charAt(i)+"").equals("-") || (name.charAt(i)+"").equals(" ")) {
                formattedName.append(name.charAt(i));
                i++;
                formattedName.append((name.charAt(i)+"").toUpperCase());
            } else {
                formattedName.append((name.charAt(i)+"").toLowerCase());
            }
        }

        return formattedName.toString();
    }

    // Method to initialize all .txt files into Arrays:
    public static String[] TxtToArray(String fileName, int txtFileLength) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String [] array = new String[txtFileLength];

        for(txtFileLength=0; txtFileLength<array.length;txtFileLength++){
            array[txtFileLength]=scanner.nextLine();
        }

        scanner.close();

        return array;

    }

    // Method to initialize all .txt files into Arrays:
    public static String[] TxtToArrayStringFormatted(String fileName, int txtFileLength) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String [] array = new String[txtFileLength];

        for(txtFileLength=0; txtFileLength<array.length;txtFileLength++){
            array[txtFileLength]=formattedName(scanner.nextLine());
        }

        scanner.close();

        return array;

    }

    // Method to initialize all .txt files into Arrays:
    public static String[] TxtToArrayIntFormatted(String fileName, int txtFileLength) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String [] array = new String[txtFileLength];
        final int factor = 10;

        for(txtFileLength=0; txtFileLength<array.length;txtFileLength++){
            double x = ((Math.floor((Double.parseDouble(scanner.nextLine()))*factor))/factor);
            array[txtFileLength] = String.valueOf(x);
        }

        scanner.close();

        return array;

    }

    // Method for formatting individual table data
    public static String tableDataFormatted(String dataName, String arrayData) {

        return "<th class=\"rightText\" id="+dataName+">"+arrayData+"</td>";

    }

    // Method for formatting all arrays and strings into a singular output
    public static String HTMLCode(
            int arrayLength, String [] allHomerooms, String [] allDonations, String [] allNames,
            String [] allGrades, String [] allTotalKilometres) {

        StringBuilder HTMLcode = new StringBuilder();

        HTMLcode.append("\t").append("<tbody>").append("\n")
                .append("\t").append("\t").append("<tr>").append("\n");

        for(i = 0; i < arrayLength; i++) {

            i++; String rank = Integer.toString(i); i--;

            if(i == 0) {
                HTMLcode.append("\t").append("\t").append("\t");
            } else {
                HTMLcode.append("\n").append("\t").append("\t").append("\t");
            }

            HTMLcode.append(tableDataFormatted("ranks", rank)).append("\n")
                    .append("\t").append("\t").append("\t").append(tableDataFormatted("homeroom", allHomerooms[i])).append("\n")
                    .append("\t").append("\t").append("\t").append(tableDataFormatted("name", allNames[i])).append("\n")
                    .append("\t").append("\t").append("\t").append(tableDataFormatted("grade", allGrades[i])).append("\n")
                    .append("\t").append("\t").append("\t").append(tableDataFormatted("kms", allTotalKilometres[i])).append("\n")
                    .append("\t").append("\t").append("\t").append(tableDataFormatted("donation", allDonations[i])).append("\n")
                    .append("\t").append("\t").append("</tr>").append("\n");

            if(i == arrayLength-1) {
                HTMLcode.append("");
            } else {
                HTMLcode.append("\t").append("\t").append("<tr>");
            }

        }

        HTMLcode.append("\t").append("</tbody>");

        return HTMLcode.toString();

    }

    public static void main(String [] args) throws FileNotFoundException {

        final int maxArrayLength = ArrayMax(namesTxt);

        final String [] namesArray = TxtToArrayStringFormatted(namesTxt, maxArrayLength);
        final String [] kmsArray = TxtToArrayIntFormatted(kilometresTxt, maxArrayLength);
        final String [] gradesArray = TxtToArray(gradesTxt, maxArrayLength);
        final String [] homeroomsArray = TxtToArray(homeroomTxt, maxArrayLength);
        final String [] donationsArray = TxtToArrayIntFormatted(donationsTxt, maxArrayLength);

        String finalCode = HTMLCode(maxArrayLength, homeroomsArray, donationsArray, namesArray, gradesArray, kmsArray);

        System.out.println(finalCode);

    }
}