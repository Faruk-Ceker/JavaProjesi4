import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UyeManager extends Veritabani {
    static Scanner scan = new Scanner(System.in);

    public static void uyeMenu() throws InterruptedException {
        String tercih = "";

        do { //TODO Kullanıcı Çıkış Yapmadığı Sürece, Menüyü Görmeye Devam Etsin...

            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                            "================= UYE MENU =================\n" +
                            "\n" +
                            "\t   1- Uye Listesi Yazdir\t\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Bilgilerini Girerek Uye Ekleme\n" +
                            "\t   5- Kimlik No Ile Kayit Silme \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS\n");

            tercih=scan.nextLine().toLowerCase();
            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...
            switch (tercih){

                // Uye Listesi Yazdir
                case "1" : uyeListesiYazdir(); break;
                case "2" : soyisimdenUyeBulma(); break;
                // Soyisimden Uye Bulma

                // Sehre Gore Uye Bulma
                case "3" : sehreGoreUyeBulma(); break;
                // Bilgilerini Girerek Uye Ekleme
                case "4" : uyeEkleme(); break;
                // Kimlik No Ile Kayit Silme
                case "5" : tcNoIleUyeSil(); break;
                case "a" : Helper.anaMenu(); break;
                case "q" : Helper.projeDurdur(); break;
                default  : System.out.println("Lutfen gecerli tercih yapiniz: ");
            }

        }while (!tercih.equals("q"));

    }

    public static void tcNoIleUyeSil() throws InterruptedException {

        //TODO Kullanıcıdan alacağınız kimlik no ile ilgili üyeyi kayıtlardan siliniz...
        System.out.println("Silinecek uyeye ait kimlik no giriniz: ");

        String silinecekValue=scan.nextLine();
        //TODO Gerekli atamaları yapınız. Aşağıdaki try-catch bloğu yardımcı olabilir...

        if (uyelerMap.containsKey(silinecekValue))
            uyelerMap.remove(silinecekValue);

        System.out.println(silinecekValue+ " Tc Kimlik No' lu Üye Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println("\n"+silinecekValue+ " Tc Kimlik No' lu Üye Başarıyla Silindi.");

//        try {
//            boolean sonuc = sonucValue.equals(silinecekValue);
//        } catch (Exception e) {
//            System.out.println("Istediginiz Tc numarasi ile uye bulunamadi.");
//        }
    }

    public static void uyeEkleme() throws InterruptedException {

        //TODO Kullanıcıdan Tc no , Isim, Soyisim, Sehir, Dogum Yili alınız...
        //TODO Aldığınız değeri UyelerMap mapine uygun şekilde ekleyiniz...
        System.out.println("Eklenecek üyenin Tc Kimlik No'sunu giriniz: ");
        String tcNo=scan.nextLine();
        System.out.println("Eklenecek üyenin İsmini giriniz: ");
        String isim=scan.nextLine();
        System.out.println("Eklenecek üyenin Soyismini giriniz: ");
        String soyisim=scan.nextLine();
        System.out.println("Eklenecek üyenin Şehirini giriniz: ");
        String sehir=scan.nextLine();
        System.out.println("Eklenecek üyenin Doğum Yılını giriniz: ");
        String dogYili=scan.nextLine();
        String bilgiler=isim+", "+soyisim+", "+sehir+", "+dogYili;
        uyelerMap.put(tcNo,bilgiler);

        System.out.println("Yeni üye ekleniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }


        System.out.println("\nÜye başarılı bir şekilde eklendi.");
    }

    public static void sehreGoreUyeBulma() throws InterruptedException {

        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde şehir araması yapın;
        //TODO Girilen şehire sahip tüm üyeleri map veya liste olarak döndürünüz...
        boolean dogruCalisti = true;
        do {
            try {
        System.out.println("Aradiginiz Uyenin Sehrini Giriniz:");
        String araSehir = scan.nextLine().toLowerCase();

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();
        System.out.println(
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                        "=============== SEHIR ILE UYE ARAMA ===============\n" +
                        "TcNo : Isim , Soyisim , Sehir, D.Yili");
        for ( Map.Entry<String, String> m : uyelerEntrySet){
            String [] tumValue = m.getValue().split(", ");
            if (tumValue[2].toLowerCase().contains(araSehir))
                System.out.println(m.getKey()+" : "+m.getValue());

        }
                dogruCalisti = true;
            }catch (Exception e){

                dogruCalisti = false;
                System.out.println("Beklenmeyen bir hata ile karşılaşıldı.");
            }
        }while (!dogruCalisti);
        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir... Zorunlu değil...

    }

    public static void soyisimdenUyeBulma() throws InterruptedException {
        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde soyisim araması yapın;
        //TODO Girilen soyismine sahip tüm üyeleri map veya liste olarak döndürünüz...

        boolean dogruCalisti = true;
        do {
        try {
        System.out.println("Aradiginiz uyenin soyisminin tamamini ya da bir kismini giriniz: ");
        String araSoyad = scan.nextLine().toLowerCase();

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.println(
                "\n========== TECHNO STUDY BOOTCAMP ===========\n" +
                        "=========== SOYISIM ILE UYE ARAMA ==========\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        for ( Map.Entry<String, String> m : uyelerEntrySet){
            String [] tumValue = m.getValue().split(", ");
            if (tumValue[1].toLowerCase().contains(araSoyad))
                System.out.println(m.getKey()+" : "+m.getValue());


        }
        dogruCalisti=true;

        }catch (Exception e){

            dogruCalisti = false;
            System.out.println("Beklenmeyen bir hata ile karşılaşıldı.");
        }
        }while (!dogruCalisti);

        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir. Şart değil, isteğe bağlı.

        //TODO Syisminin bir kısmı girilse bile bulunan üyeler listelensin...
    }

    public static void uyeListesiYazdir() throws InterruptedException {
        //İPUCU METODU: Bu metodu değiştirmenize gerek yok...

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.print("Uye Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                        "=============== UYE LISTESI ================\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir...
        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
    }
}
