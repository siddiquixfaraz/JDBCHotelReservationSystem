import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String userName = "root";
    private static final String password = "Siddiqui@12";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }

        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
           while (true){
            System.out.println("HOTEL RESERVATION SYSTEM");
            System.out.println("1. Reserve a room");
            System.out.println("2. View Reservation");
            System.out.println("3. Get Room number");
            System.out.println("4. Update Reservation");
            System.out.println("5. Delete Reservation");
            System.out.println("6. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    reserveRoom(connection,sc);
                    break;
                case 2 :
                    viewReservation(connection,sc);
                    break;
                case 3 :
                    getRoomNumber(connection,sc);
                    break;
                case 4 :
                    updateReservation(connection,sc);
                    break;
                case  5 :
                    deleteReservation(connection,sc);
                    break;
                case  6 :
                    return;
                default:
                    System.out.println("Invalid Choice!!!");
            }
        }
        }catch (SQLException e){
            System.out.println(e);
        }




    }

    private static void reserveRoom(Connection connection , Scanner sc ){
        System.out.println("Enter guest name: ");
        String guestName = sc.next();
        System.out.println("Enter room number: ");
        int roomNo = sc.nextInt();
        System.out.println("Enter Phone number: ");
        int phoneNo = sc.nextInt();

        String sqlQuery = "INSERT INTO RESERVATIONS(GUEST_NAME , ROOM_NUMBER, CONTACT_NUMBER) " +
                "VALUES('"+guestName+"' , '"+roomNo+"', '"+phoneNo+"');";

        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(sqlQuery);

            if (rowAffected > 0){
                System.out.println("Reservation Created Successfully!");
            }else {
                System.out.println("Insertion Failed");
            }

        }catch (SQLException e){
            System.out.println(e);
        }

    }

    private static void viewReservation(Connection connection , Scanner sc ){

        String query = "SELECT * FROM RESERVATIONS;";
        System.out.println("Current Reservations");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int reservationID = resultSet.getInt("reservation_id");
                String guestName = resultSet.getString("guest_name");
                String roomNo = resultSet.getString("room_number");
                String contactNo = resultSet.getString("contact_number");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                System.out.println(guestName + " " + roomNo + " " + contactNo + " " + reservationDate);

            }
        }catch (SQLException e){
            System.out.println(e);
        }


    }

    private static void getRoomNumber(Connection connection , Scanner sc ){
        System.out.println("Enter Room ID");
        int roomID = sc.nextInt();

        String query = "SELECT room_number FROM RESERVATIONS where id = '"+roomID+"';";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            int roomNo = resultSet.getInt("room_number");
            System.out.println("Room No is :"+ roomNo);
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    private static void updateReservation(Connection connection , Scanner sc ){

    }

    private static void deleteReservation(Connection connection , Scanner sc ){

    }

    private static void exit(){

    }

}
