package DbLogic;

import connect.Dbconnect;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.Scanner;

public class DbUtil {
    public static void registerUser() throws SQLException {
        try {
            Connection connection = Dbconnect.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call add_user(?,?,?,?)}");
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String userName = scanner.nextLine();
            System.out.print("Enter your email: ");
            String userEmail = scanner.next();
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.setString(1, userName);
            callableStatement.setString(2, userEmail);
            int i = 0;
            while (i < 4) {
                System.out.print("Create a password for your account: ");
                String userPassword = scanner.next();
                System.out.print("Confirm your password: ");
                String userPassword1 = scanner.next();
                if (userPassword.equals(userPassword1)) {
                    callableStatement.setString(3, userPassword);
                    break;
                } else {
                    System.out.println("Your confirmation password is incorrect ! ! ! ");
                }
                i++;
            }
            callableStatement.execute();
            String message = callableStatement.getString(4);
            System.out.println(message);
            callableStatement.close();
            connection.close();
        } catch (PSQLException e) {
            System.out.println("Parol uzunligi 5 dan kam bo'lmasligi kerak.");
        }
    }

    public static void addTrain() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Poyezd modelini kiriting: ");
        String trainModel = scanner.next();
        System.out.print("Poyezd turini kiriting: ");
        String trainType = scanner.next();
        Connection connection = Dbconnect.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call add_train(?,?,?)}");
        callableStatement.registerOutParameter(3, Types.VARCHAR);
        callableStatement.setString(1, trainModel);
        callableStatement.setString(2, trainType);
        callableStatement.execute();
        String message = callableStatement.getString(3);
        System.out.println(message);
        callableStatement.close();
        connection.close();
    }

    public static void addTicket() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Joyni kiriting: ");
        String seat = scanner.nextLine();
        System.out.print("Narxini kiriting: ");
        String price = scanner.nextLine();
        System.out.print("Klass ni kiriting: ");
        String klass = scanner.next();
        System.out.print("Yo'nalish ID sini kiriting: ");
        int directionId = scanner.nextInt();
        System.out.print("Poyezd ID sini kiriting: ");
        int trainId = scanner.nextInt();
        System.out.println("Biletlar sonini kiriting: ");
        int numberTicket = scanner.nextInt();
        Connection connection = Dbconnect.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call add_ticket(?,?,?,?,?,?,?,?)}");
        callableStatement.registerOutParameter(8, Types.VARCHAR);
        callableStatement.setString(1, seat);
        callableStatement.setString(2, price);
        callableStatement.setString(3, klass);
        callableStatement.setInt(4, directionId);
        callableStatement.setInt(5, trainId);
        callableStatement.setInt(6, numberTicket);
        while (true) {
            System.out.println("Biletni sotib olsa bo'ladimi(ha yoki yo'q)");
            String available = scanner.next();
            if (available.equals("ha")) {
                callableStatement.setBoolean(7, true);
                break;
            } else if (available.equals("yo'q")) {
                callableStatement.setBoolean(7, false);
                break;
            } else {
                System.out.println("Noto'g'ri so'z kiritdinggiz,Iltimos ha yoki yo'q degan so'zni kiriting ! ! ! ");
            }
        }
        callableStatement.execute();
        System.out.println(callableStatement.getString(8));
        callableStatement.close();
        connection.close();
    }

    public static void addDirection() throws SQLException {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Jo'nab ketish manzilini kiriting: ");
            String goPlace = scanner.nextLine();
            System.out.print("Yetib borish manzilini kiriting: ");
            String arrivePlace = scanner.nextLine();
            System.out.println("Jo'nab ketish vaqtini kiriting: ");
            String goTime = scanner.nextLine();
            System.out.print("Yetib borish vaqtini kiriting: ");
            String arriveTime = scanner.nextLine();
            System.out.print("Yurish davomiyligini kiriting: ");
            String duration = scanner.nextLine();
            System.out.println("Poyezd ID sini kiriting: ");
            int trainId = scanner.nextInt();

            //Connect DAtabase
            Connection connection = Dbconnect.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call add_user(?,?,?,?,?,?,?)}");
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.setString(1, goPlace);
            callableStatement.setString(2, arrivePlace);
            callableStatement.setString(3, goTime);
            callableStatement.setString(4, arriveTime);
            callableStatement.setString(5, duration);
            callableStatement.setInt(6, trainId);
            callableStatement.execute();
            System.out.println(callableStatement.getString(7));
            callableStatement.close();
            connection.close();
        } catch (PSQLException e) {
            System.out.println("XATOLIK YUZ BERDI ! ! ! ");
        }
    }

    public static void changePassword() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Emailni kiriting: ");
        String email = scanner.nextLine();
        System.out.println("Parolni kiriting: ");
        String password = scanner.nextLine();
        Connection connection = Dbconnect.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call change_user_password(?,?)}");
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            if (resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
                System.out.print("Yangi parolni kiriting: ");
                String newPassword = scanner.nextLine();
                System.out.print("Parolni tasdiqlang: ");
                String confrimPassword = scanner.nextLine();
                if (newPassword.equals(confrimPassword)) {
                    callableStatement.setString(1, newPassword);
                } else {
                    System.out.println("Parolni tasdig'i noto'g'ri");
                }
            } else {
                System.out.println("Email noto'g'ri kiritildi ! ! ! ");
            }
        }
        callableStatement.execute();
        System.out.println(callableStatement.getString(2));
        callableStatement.close();
        connection.close();
    }

    public static void deleteAccount() throws SQLException {
        try {
            boolean check = false;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Emailinggizni kiriting: ");
            String email = scanner.nextLine();
            System.out.println("Parolinggizni kiriting: ");
            String password = scanner.nextLine();
            Connection connection = Dbconnect.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            CallableStatement callableStatement = connection.prepareCall("{call delete_user(?,?,?)}");
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            while (resultSet.next()) {
                if (resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
                    check = true;
                    break;
                } else {
                    check = false;
                }
            }
            if (check == true) {
                callableStatement.setString(1, email);
                callableStatement.setString(2, password);
                callableStatement.execute();
                System.out.println(callableStatement.getString(3));
                callableStatement.close();
                connection.close();
            } else {
                System.out.println("Foydalanuvchi topilmadi ! ! !");
            }
        } catch (PSQLException e) {
            System.out.println("Xatolik yuz berdi");
        }
    }

    public static void deleteTrain() throws SQLException {
        try {
            Connection connection = Dbconnect.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet3 = statement.executeQuery("select train.id,model,type,go_place,arrive_place," +
                    "go_time,arrive_time from train join direction on(train.id=direction.train_id)");
            while (resultSet3.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet3.getInt("id"));
                System.out.println("Model: " + resultSet3.getString("model"));
                System.out.println("Turi: " + resultSet3.getString("type"));
                System.out.println("Jo'nash manzili: " + resultSet3.getString("go_place"));
                System.out.println("Yetib borish manzili: " + resultSet3.getString("arrive_place"));
                System.out.println("Jo'nash vaqti: " + resultSet3.getString("go_time"));
                System.out.println("Yetib borish vaqti: " + resultSet3.getString("arrive_time"));
                System.out.println();
            }
            boolean check = false;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Poyezd ID sini kiriting: ");
            int id = scanner.nextInt();
            ResultSet resultSet = statement.executeQuery("select * from train");
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    check = true;
                    break;
                } else {
                    check = false;
                }
            }
            if (check = true) {
                CallableStatement callableStatement = connection.prepareCall("{call delete_train(?,?)}");
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.setInt(1, id);
                callableStatement.execute();
                System.out.println(callableStatement.getString(2));
                callableStatement.close();
                connection.close();
            }
        } catch (PSQLException e) {
            System.out.println("Xatolik yuz berdi...");
        }
    }

    public static void deleteDirection() throws SQLException {
        try {
            Connection connection = Dbconnect.getConnection();
            Statement statement = connection.createStatement();
            System.out.println("Qaysi yo'nalishni o'chirmoqchisiz ? ? ? ");
            ResultSet resultSet3 = statement.executeQuery("select * from direction");
            while (resultSet3.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet3.getInt("id"));
                System.out.println("Jo'nash manzili: " + resultSet3.getString("go_place"));
                System.out.println("Yetib borish manzili: " + resultSet3.getString("arrive_place"));
                System.out.println("Jo'nash vaqti: " + resultSet3.getString("go_time"));
                System.out.println("Yetib borish vaqti: " + resultSet3.getString("arrive_time"));
                System.out.println();
            }
            boolean check = false;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Yo'nalish ID sini kiriting: ");
            int id = scanner.nextInt();
            System.out.println("Poyezd ID sini kiriting: ");
            int trainId = scanner.nextInt();
            ResultSet resultSet = statement.executeQuery("select * from direction");
            while (resultSet.next()) {
                if (id == resultSet.getInt("id") && trainId == resultSet.getInt("train_id")) {
                    check = true;
                    break;
                } else {
                    check = false;
                }
            }
            if (check == true) {
                CallableStatement callableStatement = connection.prepareCall("{call delete_direction(?,?,?)}");
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.setInt(1, id);
                callableStatement.setInt(2, trainId);
                callableStatement.execute();
                System.out.println(callableStatement.getString(3));
                callableStatement.close();
                connection.close();
            } else {
                System.out.println("Yo'nalish ma'lumotlari noto'g'ri kiritildi yoki Ma'lumot topilmadi");
            }
        } catch (PSQLException e) {
            System.out.println();
            System.out.println("Xatolik yuz berdi...");
        }
    }

    public static void deleteTicket() throws SQLException {
        try {
            Connection connection = Dbconnect.getConnection();
            Statement statement = connection.createStatement();
            System.out.println("Qaysi biletni o'chirmoqchisiz ?");

            ResultSet resultSet3 = statement.executeQuery("select ticket.id,place,summa,class,go_place,arrive_place," +
                    "go_time,arrive_time from ticket join direction on(ticket.direction_id=direction.id)");
            while (resultSet3.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet3.getString("id"));
                System.out.println("O'rin: " + resultSet3.getString("place"));
                System.out.println("Narxi: " + resultSet3.getString("summa"));
                System.out.println("Klass: " + resultSet3.getString("class"));
                System.out.println("Jo'nash manzili: " + resultSet3.getString("go_place"));
                System.out.println("Yetib borish manzili: " + resultSet3.getString("arrive_place"));
                System.out.println("Jo'nash vaqti: " + resultSet3.getString("go_time"));
                System.out.println("Yetib borish vaqti: " + resultSet3.getString("arrive_time"));
                System.out.println();
            }

            boolean check = false;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Bilet ID sini kiriting: ");
            int id = scanner.nextInt();
            ResultSet resultSet = statement.executeQuery("select * from ticket");
            while (resultSet.next()) {
                if (id == resultSet.getInt("id")) {
                    check = true;
                    break;
                } else {
                    check = false;
                }
            }
            if (check == true) {
                CallableStatement callableStatement = connection.prepareCall("{call delete_ticket(?,?)}");
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.setInt(1, id);
                callableStatement.execute();
                System.out.println(callableStatement.getString(2));
                callableStatement.close();
                connection.close();
            } else {
                System.out.println("Bilet ma'lumotlari noto'g'ri kiritildi yoki Ma'lumot topilmadi");
            }
        } catch (PSQLException e) {
            System.out.println("Xatolik yuz berdi....");
        }
    }

    public static void chooseDirection() throws SQLException {
        boolean check = false;
        Connection connection = Dbconnect.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from direction");
        while (resultSet.next()) {
            System.out.println();
            System.out.println("Jo'nab ketish manzili: " + resultSet.getString("go_place"));
            System.out.println("Yetib borish manzili: " + resultSet.getString("arrive_place"));
            System.out.println();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jo'nab ketish manzilini kiriting: ");
        String goPlace = scanner.nextLine();
        System.out.println("Yetib borish manzilini kiriting: ");
        String arrivePlace = scanner.nextLine();
        ResultSet resultSet7 = statement.executeQuery("select * from direction");
        while (resultSet7.next()) {
            System.out.println(resultSet7.getString("go_place"));
            if (resultSet7.getString("go_place").equals(goPlace) && resultSet7.getString("arrive_place").equals(arrivePlace)) {
                check = true;
                break;
            } else {
                check = false;
            }
        }
        if (check == true) {
            ResultSet resultSet1 = statement.executeQuery("select ticket.id," +
                    "go_time,arrive_time,duration,place,summa,class,available,number_ticket" +
                    " from direction join \n" +
                    "ticket on(direction.id=ticket.direction_id) where direction.go_place='" + goPlace + "' and direction.arrive_place='" + arrivePlace + "'");
            while (resultSet1.next()) {
                System.out.println("Qani");
                System.out.println();
                System.out.println("Bilet ID: " + resultSet1.getString("id"));
                System.out.println("Ketish vaqti: " + resultSet1.getString("go_time"));
                System.out.println("Yetib borish vaqti: " + resultSet1.getString("arrive_time"));
                System.out.println("Davomiylik: " + resultSet1.getString("duration"));
                System.out.println("O'rin: " + resultSet1.getString("place"));
                System.out.println("Narxi: " + resultSet1.getString("summa"));
                System.out.println("Klass: " + resultSet1.getString("class"));
                System.out.println("Sotib olsa bo'ladimi: " + resultSet1.getString("available"));
                System.out.println("Qolgan biletlar soni: " + resultSet1.getString("number_ticket"));
                System.out.println();
            }
        } else {
            System.out.println("Yo'nalish no'to'g'ri kiritildi !  !  !");
        }


    }

    public static void buyTicket() throws SQLException {
        boolean checkUser = false;
        boolean checkTicket = false;
        boolean checkTicketId = false;
        Connection connection = Dbconnect.getConnection();
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.println("O'z ID raqaminggizni kiriting");
        int idUser = scanner.nextInt();
        ResultSet resultSet2 = statement.executeQuery("select * from users where id=" + idUser);
        while (resultSet2.next()) {
            if (resultSet2.getInt("buy_ticket_id") == 0) {
                checkTicketId = true;
            } else {
                checkTicketId = false;
            }
        }
        if (checkTicketId == true) {
            System.out.println("Chipta ID sini kiriting: ");
            int idTicket = scanner.nextInt();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                if (resultSet.getInt("id") == idUser) {
                    checkUser = true;
                    break;
                } else {
                    checkUser = false;
                }
            }
            if (checkUser == true) {
                ResultSet resultSet1 = statement.executeQuery("select * from ticket");
                while (resultSet1.next()) {
                    if (resultSet1.getInt("id") == idTicket) {
                        checkTicket = true;
                        break;
                    } else {
                        checkTicket = false;
                    }
                }
            } else {
                System.out.println("ID noto'g'ri kirtildi yoki ma'lumot mavjud emas");
                return;
            }
            if (checkTicket == true) {
                CallableStatement callableStatement = connection.prepareCall("{call buy_ticket(?,?,?)}");
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.setInt(1, idUser);
                callableStatement.setInt(2, idTicket);
                callableStatement.execute();
                System.out.println(callableStatement.getString(3));
                callableStatement.close();
                connection.close();
            } else if (checkTicket == false) {
                System.out.println("ID lar noto'g'ri kiritildi");
            }
        } else {
            System.out.println("Sizda bilet mavjud ! ! !");
        }
    }

    public static void minusTicketNum() throws SQLException {
        Connection connection = Dbconnect.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call buy_ticket2(?)}");
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.execute();
        System.out.println(callableStatement.getString(1));
        callableStatement.close();
        connection.close();
    }

    public static void cancelTicket() throws SQLException {
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        Connection connection = Dbconnect.getConnection();
        Statement statement = connection.createStatement();
        System.out.print("O'z id raqaminggizni kiriting: ");
        int id = scanner.nextInt();
        ResultSet resultSet = statement.executeQuery("select * from users where id=" + id);
        while (resultSet.next()) {
            if (resultSet.getInt("buy_ticket_id") != 0) {
                check = true;
            } else {
                check = false;
            }
        }
        if (check == true) {
            CallableStatement callableStatement = connection.prepareCall("{call cancel_ticket(?,?)}");
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.setInt(1, id);
            callableStatement.execute();
            System.out.println(callableStatement.getString(2));
            callableStatement.close();
            connection.close();

        } else {
            System.out.println("Sizda bilet mavjud emas ! ! !");
        }
    }

    public static void adminEnter(String password, String email1) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        Connection connection = Dbconnect.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from admin");
        while (resultSet.next()) {
            if (resultSet.getString("email").equals(email1) && resultSet.getString("password").equals(password)) {
                check = true;
                break;
            } else {
                check = false;
            }
        }
        if (check == true) {

        } else {
            System.out.println("Parol yoki email noto'g'ri  ! ! !");
            System.exit(0);
        }
    }

    public static void userEnter(String password2, String email2) throws SQLException {
        boolean check = false;
        Connection connection = Dbconnect.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            if (resultSet.getString("email").equals(email2) && resultSet.getString("password").equals(password2)) {
                check = true;
                break;
            } else {
                check = false;
            }
        }
        if (check == true) {

        } else {
            System.out.println("Parol yoki email noto'g'ri  ! ! !");
            System.exit(0);
        }
    }

    public static void showTicket(String emails) throws SQLException {
        try {
            Connection connection = Dbconnect.getConnection();
            Statement statement = connection.createStatement();
            boolean check = false;
            ResultSet resultSet = statement.executeQuery("select name,place,summa,class,direction_id," +
                    "train_id from users join ticket on(users.buy_ticket_id=ticket.id) where users.email='" + emails + "'");
            while (resultSet.next()) {
                System.out.println("ISM: " + resultSet.getString("name"));
                System.out.println("Joy: " + resultSet.getString("place"));
                System.out.println("Narxi: " + resultSet.getString("summa"));
                System.out.println("Klass: " + resultSet.getString("class"));
                System.out.println("Yo'nalish ID si: " + resultSet.getInt("direction_id"));
                System.out.println("Poyezd ID: " + resultSet.getInt("train_id"));
            }
        } catch (PSQLException e) {
            System.out.println("Xatolik yuz berdi");
        }
    }

    public static void info() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Email");
        String email1 = scanner.next();
        Connection connection = Dbconnect.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where email='" + email1 + "'");
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Email: " + resultSet.getString("email"));
            System.out.println("Password: " + "*********");
        }


    }

    public static void trainInfo() {
        try {
            Connection connection = Dbconnect.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from train");
            while (resultSet.next()) {
                System.out.println("*********************************************");
                System.out.println("Id of a Train: " + resultSet.getInt(1));
                System.out.println("Model of a Train: " + resultSet.getString(2));
                System.out.println("Type of a Train: " + resultSet.getString(3));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("Bazadan ma'lumot olinmadi ! ! ! !  ");
        }

    }

    public static void directionInfo() {
        try {
            Connection connection = Dbconnect.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from direction join train on train.id=direction.train_id");
            while (resultSet.next()) {
                System.out.println();
                System.out.println("Yo'nalish ID: " + resultSet.getInt(1));
                System.out.println("Poyezd jo'nab ketish manzili: " + resultSet.getString(2));
                System.out.println("Poyezd yetib borish manzili: " + resultSet.getString(3));
                System.out.println("Poyezd jo'nab ketish vaqti: " + resultSet.getString(4));
                System.out.println("Poyezd yetib borish vaqti: " + resultSet.getString(5));
                System.out.println("Poyezd yurish davomiyligi:  " + resultSet.getString(6));
                System.out.println("Poyezd ID: " + resultSet.getInt(7));
                System.out.println("Poyezd modeli: " + resultSet.getString(9));
                System.out.println("Poyezd turi: " + resultSet.getString(10));
                System.out.println();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void ticketInfo() {
        try {
            Connection connection = Dbconnect.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ticket join direction on ticket.direction_id=direction.id\n ");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt(1));
                System.out.println("Joyi: " + resultSet.getString(2));
                System.out.println("Summasi: " + resultSet.getString(3));
                System.out.println("Klass: " + resultSet.getString(4));
                System.out.println("Yo'nalish ID si: " + resultSet.getInt(5));
                System.out.println("Poyezd ID si: " + resultSet.getInt(6));
                System.out.println("Sotib olib bo'ladigan biletlar soni: " + resultSet.getInt(8));
                System.out.println("Jo'nash  Manzili: " + resultSet.getString(10));
                System.out.println("Yetib kelish manzili: " + resultSet.getString(11));
                System.out.println("Jo'nash vaqti: " + resultSet.getString(12));
                System.out.println("Yetib borish vaqti: " + resultSet.getString(13));
                System.out.println("Davomuyligi: " + resultSet.getString(14));
                statement.close();
                connection.close();

            }

        } catch (SQLException throwables) {
            System.out.println("Ma'lumot olib kelishda xatolik yuz berdi ! ! !");
        }
    }
}
