import java.util.Scanner;

public class Helper {
    static Scanner scan = new Scanner(System.in);

    private static String loggedInUsername;
    private static UserRole loggedInUserRole;

    private static void login() throws InterruptedException {   //TODO LOGIN FONKSIYONUNU YAZIN
        // login olunduğunda, "loggedInUserRole" ya ADMIN ya USER rolünü alacak, eğer null ise
        // login olunmamış demektir...

        if (loggedInUserRole == null) {
            loginAndShowUserMenu(UserRole.NULL);
        }


        //TODO loggedInUserRole null olup olmadığını kontrol ederek,
        //TODO kullanıcıdan, kullanıcı adı ve şifre isteyiniz...

        //TODO username:password > admin:Admin123 ya da user:User123 şartının sağlanıp sağlanmadığına bakın...
        //TODO Geçerli giriş sağlanırsa kullanıcının rolünü belirleyin,
        //TODO "Admin olarak giris yapildi!" ya da "Standart kullanici olarak giris yapildi!" uygun olan mesajı verin..
        //TODO Yoksa "Gecersiz kullanici adi veya sifre!" mesajını döndürün.

    }

    public static void anaMenu() throws InterruptedException {

        login(); // Kullanıcı girişi yapılıyor

        //TODO User Role'e göre kullanıcının "showAdminMenu()" ya da "showUserMenu()" ye
        //TODO Yönlendirin...

        String tercih = "";

        do {//TODO Kullanıcıdan alacağınız tercihlere göre, gerekli menü metodlarına yönlendirme yapınız


            System.out.println("\n=========== TECHNO STUDY CONFLUENCE =============\n" +
                    "=================== ANA MENU ====================\n" +
                    "\n" +
                    "\t   1- Kutuphane Bilgileri Goruntule\n" +
                    "\t   2- Uyeler Menu\n" +
                    "\t   3- Kitaplar Menu\n" +
                    "\t   Q- CIKIS\n");

            System.out.print("Lutfen Menuden tercihinizi yapiniz:");

            tercih = scan.nextLine().toLowerCase();

            switch (tercih) {
                case "1":
                    kutuphaneBilgileriniYazdir();
                    break;
                case "2":
                    UyeManager.uyeMenu();
                    break;
                case "3":
                    KitapManager.kitapMenu();
                    break;
                case "q":
                    projeDurdur();
                    break;
                default:
                    System.out.print("Lutfen gecerli bir tercih giriniz:");
            }

        } while (!tercih.equals("q"));


    }

    public static void kutuphaneBilgileriniYazdir() throws InterruptedException {//BU METODDA BİR DEĞİŞİKLİK YAPMANIZA GEREK YOK...

        System.out.print("Kutuphane bilgileri yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println("\n" +
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                "\t\t Kutuphane : " + Kutuphane.kutuphaneIsim +
                "\n\t\t Adres : " + Kutuphane.adres +
                "\n\t\t Telefon : " + Kutuphane.telefon);

    }

    private static void loginAndShowUserMenu(UserRole requiredRole) throws InterruptedException {
        boolean dogruCalisti = true;
        do {
            try {
                System.out.print("Kullanici Adi: ");
                String username = scan.nextLine();
                System.out.print("Sifre: ");
                String password = scan.nextLine();

                //Kullanıcıdan aldığınız username ve password, authenticateUser(String username, String password)
                //metodunda doğrulamaya tabi tutuluyor...
                UserRole role = authenticateUser(username, password);

                if (role.equals(UserRole.ADMIN)) {
                    System.out.println("Admin olarak giriş yapıldı...");
                    loggedInUserRole=UserRole.ADMIN;
                    showAdminMenu();
                } else if (role.equals(UserRole.USER)) {
                    System.out.println("User olarak giriş yapıldı...");
                    loggedInUserRole=UserRole.USER;
                    showUserMenu();
                }
                dogruCalisti = true;
            } catch (Exception e) {

                dogruCalisti = false;
            }
        } while (!dogruCalisti);


        //NOT : Doğrulamadan geçerse role ADMIN veya USER olur... Geçmezse "null" olur

        //TODO null ise "Gecersiz kullanici adi veya sifre." mesajı verin
        //TODO ADMIN ise showAdminMenu() metoduyla admin menüyü gösterin
        //TODO USER ise showUserMenu() metoduyla user menüyü gösterin...
        //TODO Aksi durumlarda "Bu sayfaya erisim izniniz yok." mesajını verin...
        //if-else bloklarıyla şartlar gerçeklenebilir...
    }

    private static UserRole authenticateUser(String username, String password) {

        if (username.equals("admin") && password.equals("Admin123")) {
            return UserRole.ADMIN;
        } else if (username.equals("user") && password.equals("User123")) {
            return UserRole.USER;
        } else {
            System.out.println("Hatalı kullanıcı adı veya şifre girdiniz.");

            //TODO Girilen Kullanıcı adı ve şifreyi kontrol edin,
            //TODO Geçerli kullanıcıadı ve şifreyse UserRole return edin... > ADMIN ya da USER
            return null;
        }
    }

    private static void showAdminMenu() throws InterruptedException {
        String tercih = "";
        do {
            System.out.println(
                    "\n=========== TECHNO STUDY CONFLUENCE ==========\n" +
                            "================== ADMIN MENU ================\n" +
                            "\n" +
                            "\t   1- Uyeler Menu\n" +
                            "\t   2- Kitaplar Menu\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");

            tercih = scan.nextLine().toLowerCase();
            //TODO Kullanıcıdan alacağınız tercihlere göre ilgili menüye (metoda) yönlendirme yapınız...
            switch (tercih) {
                case "1":
                    UyeManager.uyeMenu();
                    break;
                case "2":
                    KitapManager.kitapMenu();
                    break;
                case "a":
                    anaMenu();
                    break;
                case "q":
                    projeDurdur();
                    break;
                default:
                    System.out.println("Lutfen gecerli bir tercih giriniz");

            }


        } while (!tercih.equals("q"));
    }

    private static void showUserMenu() throws InterruptedException {
        String tercih = "";
        //TODO Kullanıcı Çıkış Yapmadığı Sürece User Menüde Kalsın...
        do {
            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE ===========\n" +
                            "================== USER MENU =================\n" +
                            "\n" +
                            "\t   1- Uyeleri Listele\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Kitaplari Listele\n" +
                            "\t   5- Yazardan Kitap Bulma\n" +
                            "\t   6- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   7- Kitap Odunc Al\n" +
                            "\t   8- Kitap Iade Et\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");

            //TODO Kullanıcıdan alacağınız tercihlere göre ilgili menüye (metoda) yönlendirme yapınız...

            tercih = scan.nextLine().toLowerCase();
            switch (tercih) {
                case "1":
                    UyeManager.uyeListesiYazdir();
                    break;
                case "2":
                    UyeManager.soyisimdenUyeBulma();
                    break;
                case "3":
                    UyeManager.sehreGoreUyeBulma();
                    break;
                case "4":
                    KitapManager.kitapListesiYazdir();
                    break;
                case "5":
                    KitapManager.yazardanKitapBulma();
                    break;
                case "6":
                    KitapManager.turVeyaYayinTarihiIleKitapBulma();
                    break;
                case "7":
                    KitapManager.kitapOduncAl();
                    break;
                case "8":
                    KitapManager.kitapIadeEt();
                    break;
                case "a":
                    anaMenu();
                    break;
                case "q":
                    projeDurdur();
                    break;
                default:
                    System.out.println("Lutfen gecerli bir tercih giriniz");

            }


        } while (!tercih.equals("q"));
    }

    public static void projeDurdur() {
        System.out.println("Kutuphane projesinden ciktiniz");
        System.exit(0);
    }
}

