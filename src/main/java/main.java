import DbLogic.DbUtil;
import connect.Dbconnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Xush Kelibsiz Elektron chipta platformasiga ! ! !");
        System.out.println();
        System.out.println("1=>Admin sifatida kirish \n2=>Foydalanuvchi sifatida kirish" +
                "\n3=>Platforma haqida");
        System.out.print("==> ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Emailni kiriting: ");
                String email2 = scanner.next();
                System.out.println("Parolni kiriting: ");
                String password = scanner.next();
                DbUtil.adminEnter(password, email2);
                System.out.println("Tizimga xush kelibsiz xurmatli Admin ! ! !");
                System.out.println();
                while (true) {
                    System.out.println("1-Poyezd qo'shish");
                    System.out.println("2-Chipta qo'shish");
                    System.out.println("3-Yo'nalish qo'shish");
                    System.out.println("4-Poyezdni o'chirish");
                    System.out.println("5-Yo'nalishni o'chirish");
                    System.out.println("6-Biletni o'chirish ");
                    System.out.println("7-Yo'nalish haqidagi ma'lumotlar");
                    System.out.println("8-Poyezd haqidagi ma'lumotlar");
                    System.out.println("9-Chipta haqidagi ma'lumotlar");
                    System.out.println("10-EXIT");
                    System.out.print("==> ");
                    int option1 = scanner.nextInt();
                    Scanner scanner1 = new Scanner(System.in);
                    switch (option1) {
                        case 1:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz poyezd qo'shish bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                                    DbUtil.addTrain();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                            break;
                        case 2:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz chipta qo'shish bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                                    DbUtil.addTicket();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                            break;
                        case 3:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz yo'nalish qo'shish bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                                    DbUtil.addDirection();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                            break;
                        case 4:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz poyezd o'chirish bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                                    DbUtil.deleteTrain();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                        case 5:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz yo'nalishni o'chirish bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                                    DbUtil.deleteDirection();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                            break;
                        case 6:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz biletni o'chirish bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                                    DbUtil.deleteTicket();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                            break;
                        case 7:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz yo'nalish ma'lumotlari bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                                    DbUtil.directionInfo();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                            break;
                        case 8:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz poyezd ma'lumotlari bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                              DbUtil.trainInfo();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                            break;
                        case 9:
                            while (true) {
                                System.out.println();
                                System.out.println("Siz bilet ma'lumotlari bo'limini tanladinggiz ! ! !");
                                System.out.println("0=>Bosh menyu");
                                System.out.println("1=>Davom etish");
                                System.out.print("==>");
                                int opt = scanner.nextInt();
                                if (opt == 0) {
                                    break;
                                } else if (opt == 1) {
                              DbUtil.ticketInfo();
                                } else {
                                    System.out.println("Noto'g'ri tanlov tanlandi");
                                }
                            }
                            break;
                        case 10:
                            System.exit(0);
                            break;
                    }
                }

            case 2:
                System.out.println("Xush kelibsiz Xurmatli foydalanuvchi");
                while (true) {
                    System.out.println();
                    System.out.println("1-Ro'yhatdan O'tish");
                    System.out.println("2-Mavjud akkountga kirish");
                    System.out.println("3-Chiqish");
                    System.out.print("==> ");
                    int option4 = scanner.nextInt();
                    switch (option4) {
                        case 1:
                            DbUtil.registerUser();
                            System.out.println("Ro'yhatga olinddinggiz ! ! !");
                            break;
                        case 2:
                            System.out.println("Emailni kiriting: ");
                            String email3 = scanner.next();
                            System.out.println("Parolni kiriting: ");
                            String password3 = scanner.next();
                            DbUtil.userEnter(password3, email3);
                            System.out.println("Tizimga xush kelibsiz xurmatli Foydalanuvchi ! ! !");
                            System.out.println();
                            while (true) {
                                Scanner scanner5 = new Scanner(System.in);
                                System.out.println("1=>Yo'nalish tanlash");
                                System.out.println("2=>Bilet sotib olish");
                                System.out.println("3=>Sotib olingan biletni bekor qilish");
                                System.out.println("4=>Parolni almashtirish");
                                System.out.println("5=>Akkauntni o'chirish");
                                System.out.println("6=>Men sotib olgan Bilet");
                                System.out.println("7=>Mening ma'lumotlarim");
                                System.out.println("8=>Exit");
                                System.out.print("==> ");
                                int option5 = scanner5.nextInt();
                                switch (option5) {
                                    case 1:
                                        while (true) {
                                            System.out.println();
                                            System.out.println("Siz yo'nalish tanlash bo'limini tanladinggiz ! ! !");
                                            System.out.println("0=>Bosh menyu");
                                            System.out.println("1=>Davom etish");
                                            System.out.print("==>");
                                            int opt = scanner.nextInt();
                                            if (opt == 0) {
                                                break;
                                            } else if (opt == 1) {
                                                DbUtil.chooseDirection();
                                            } else {
                                                System.out.println("Noto'g'ri tanlov tanlandi");
                                            }
                                        }
                                        break;
                                    case 2:
                                        while (true) {
                                            System.out.println();
                                            System.out.println("Siz bilet sotib olish bo'limini tanladinggiz ! ! !");
                                            System.out.println("0=>Bosh menyu");
                                            System.out.println("1=>Davom etish");
                                            System.out.print("==>");
                                            int opt = scanner.nextInt();
                                            if (opt == 0) {
                                                break;
                                            } else if (opt == 1) {
                                                DbUtil.buyTicket();
                                                DbUtil.minusTicketNum();
                                            } else {
                                                System.out.println("Noto'g'ri tanlov tanlandi");
                                            }
                                        }
                                        break;
                                    case 3:
                                        while (true) {
                                            System.out.println();
                                            System.out.println("Siz biletni bekor qilish bo'limini tanladinggiz ! ! !");
                                            System.out.println("0=>Bosh menyu");
                                            System.out.println("1=>Davom etish");
                                            System.out.print("==>");
                                            int opt = scanner.nextInt();
                                            if (opt == 0) {
                                                break;
                                            } else if (opt == 1) {
                                                DbUtil.cancelTicket();
                                            } else {
                                                System.out.println("Noto'g'ri tanlov tanlandi");
                                            }
                                        }
                                        break;
                                    case 4:
                                        while (true) {
                                            System.out.println();
                                            System.out.println("Siz parolni almashtirish bo'limini tanladinggiz ! ! !");
                                            System.out.println("0=>Bosh menyu");
                                            System.out.println("1=>Davom etish");
                                            System.out.print("==>");
                                            int opt = scanner.nextInt();
                                            if (opt == 0) {
                                                break;
                                            } else if (opt == 1) {
                                                DbUtil.changePassword();
                                            } else {
                                                System.out.println("Noto'g'ri tanlov tanlandi");
                                            }
                                        }
                                        break;
                                    case 5:
                                        while (true) {
                                            System.out.println();
                                            System.out.println("Siz akkauntni o'chirish bo'limini tanladinggiz ! ! !");
                                            System.out.println("0=>Bosh menyu");
                                            System.out.println("1=>Davom etish");
                                            System.out.print("==>");
                                            int opt = scanner.nextInt();
                                            if (opt == 0) {
                                                break;
                                            } else if (opt == 1) {
                                                DbUtil.deleteAccount();
                                            } else {
                                                System.out.println("Noto'g'ri tanlov tanlandi");
                                            }
                                        }
                                        break;
                                    case 6:
                                        while (true) {
                                            System.out.println();
                                            System.out.println("Siz mening biletim bo'limini tanladinggiz ! ! !");
                                            System.out.println("0=>Bosh menyu");
                                            System.out.println("1=>Davom etish");
                                            System.out.print("==>");
                                            int opt = scanner.nextInt();
                                            if (opt == 0) {
                                                break;
                                            } else if (opt == 1) {
                                                System.out.println("Emailinggizni kiriting: ");
                                                String email4 = scanner.next();
                                                DbUtil.showTicket(email4);
                                            } else {
                                                System.out.println("Noto'g'ri tanlov tanlandi");
                                            }
                                        }
                                        break;
                                    case 7:
                                        while (true) {
                                            System.out.println();
                                            System.out.println("Siz mening ma'lumotlarim bo'limini tanladinggiz ! ! !");
                                            System.out.println("0=>Bosh menyu");
                                            System.out.println("1=>Davom etish");
                                            System.out.print("==>");
                                            int opt = scanner.nextInt();
                                            if (opt == 0) {
                                                break;
                                            } else if (opt == 1) {
                                                DbUtil.info();
                                            } else {
                                                System.out.println("Noto'g'ri tanlov tanlandi");
                                            }
                                        }
                                        break;
                                    case 8:
                                        System.exit(0);
                                        break;
                                }
                            }

                        case 3:
                            System.exit(0);
                            break;

                    }
                }
            case 3:
                System.out.println("Assalomu alaykum xurmatli foydalanuvchi ushbu " +
                        "\n platformamiz yordamida siz respuplukamizni turli yo'nalishlariga" +
                        "\n elektron bilet sotib olishinggiz mumkin bo'ladi." +
                        "\n Platforma developeri: Sukhrob Ibrokhimov from PDP IT ACADEMY");
                break;

            default:
                System.out.println("Noto'g'ri tanlov tanlandi ! ! !");
                break;
        }

    }
}
