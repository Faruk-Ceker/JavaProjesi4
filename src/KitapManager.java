import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KitapManager extends Veritabani {

    static Scanner scan = new Scanner(System.in);

    public static void kitapMenu() throws InterruptedException {
        String tercih = "";
        do  {// TODO Kullanıcı Çıkış Yapmadığı sürece menüde kalalım...
            System.out.println(
                    "\n============ TECHNO STUDY BOOTCAMP ============\n" +
                            "================== KITAP MENU =================\n" +
                            "\t   1- Kitap Listesi Yazdir\n" +
                            "\t   2- Yazardan Kitap Bulma\n" +
                            "\t   3- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   4- Bilgilerini Girerek Kitap Ekleme\n" +
                            "\t   5- Kitap Ismi Ile Kayit Silme \t\n" +
                            "\t   6- Kitap Odunc Al \t\n" +
                            "\t   7- Kitap Iade Et \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");

            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...

            tercih=scan.nextLine().toLowerCase();
            switch (tercih) {
                case "1" : kitapListesiYazdir(); break;
                // Yazar Ismiyle Kitap Bulma
                case "2" : yazardanKitapBulma(); break;
                // Kitap Turu veya Yayin Tarihi Ile Kitap Bulma
                case "3" : turVeyaYayinTarihiIleKitapBulma(); break;
                // Bilgilerini Girerek Kitap Ekleme
                case "4" : kitapEkle(); break;
                case "5" : isimIleKitapSilme(); break;
                case "6" : kitapOduncAl(); break;
                case "7" : kitapIadeEt(); break;
                case "a" : Helper.anaMenu(); break;
                case "q" : Helper.projeDurdur(); break;
                default: System.out.println("Lutfen gecerli bir tercih giriniz");
            }

         }while (!tercih.equals("q"));

    }

    public static void kitapOduncAl() throws InterruptedException {
        System.out.println("Odunc almak istediginiz kitabin ismini giriniz: ");
        String oduncKitap = scan.nextLine();
        System.out.print(oduncKitap + " Odunc Alınıyor...");

        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }


        if (kitaplarMap.containsKey(oduncKitap)){
            String oduncValue = kitaplarMap.get(oduncKitap);
            kitaplarMap.remove(oduncKitap);
            oduncAlinanKitaplarMap.put(oduncKitap,oduncValue);
            System.out.println("\n"+oduncKitap+" isimli kitap ödünç alındı.");
        }
        else System.out.println("\nHatalı kitap ismi girdiniz.");



        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap ödünç alma metodunu tamamlayın...
        //NOT: Veritabanı'nda ödünç almayla alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Ödünç alındığında kitaplarMap 'ten düşüp bu map e eklenecek...
    }


    public static void kitapIadeEt() throws InterruptedException {
        System.out.println("Iade etmek istediginiz kitabin ismini giriniz: ");
        String iadeKitap = scan.nextLine();
        System.out.print(iadeKitap + " İade ediliyor...");

        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        if (oduncAlinanKitaplarMap.containsKey(iadeKitap)){
            String iadeValue = oduncAlinanKitaplarMap.get(iadeKitap);
            oduncAlinanKitaplarMap.remove(iadeKitap);
            kitaplarMap.put(iadeKitap,iadeValue);
            System.out.println("\n"+iadeKitap+" isimli kitap iade edildi.");
        }
        else System.out.println("\nHatalı kitap ismi girdiniz.");

        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap iade etme metodunu tamamlayın...
        //NOT: Veritabanı'nda iade etmeyle alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Kitap iade edildiğinde,  kitaplarMap 'e geri eklenmeli...
    }


    private static void isimIleKitapSilme() throws InterruptedException
    {//İPUCU METODU... Bu metodu değiştirmenize gerek yok.
        System.out.println("Silinecek kitabin ismini giriniz");
        String silinecekKitap = scan.nextLine();

        String silinecekValue = kitaplarMap.get(silinecekKitap);
        String sonucValue = kitaplarMap.remove(silinecekKitap);

        System.out.print("\n"+silinecekKitap + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println("\n"+silinecekKitap+" isimli kitap başarıyla silindi.");

        //ARTIK UYGUN YERLERDE BEKLEMEDİĞİNİZ SONUÇLAR İÇİN TRY CATCH KULLANABİLİRSİNİZ...
        //////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("\nIstediginiz kitap ismi bulunamadi");
        }/////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private static void kitapEkle() throws InterruptedException {
        //"A Tale of Two Cities", "Charles Dickens, Tarih, 1859" >> Kitap key,value su buna benzer şekilde...

        System.out.println("Eklenecek Kitap Adini Giriniz: ");
        String kitapAdi = scan.nextLine();

        System.out.println("Kitabın Yazarını Giriniz: ");
        String yazarAdi = scan.nextLine();


        KitapTuru kitapTuru=null;
        while (kitapTuru==null){
            try {
                System.out.println("Kitabın Turunu Giriniz (TARIH, POLISIYE, KURGU, ROMAN, DESTAN, TANIMLANMAMIS_TUR): ");

                String turAdi = scan.nextLine().toUpperCase();

                kitapTuru = KitapTuru.valueOf(turAdi);
            }catch (Exception e){
                System.out.println("Hatalı giriş yaptınız. Lütfen Kitap türünü tekrar giriniz.");
            }

        }


        System.out.println("Kitabın Yayinlanma Yilini Giriniz: ");
        String yayinYili = scan.nextLine();

        String kitapValue = yazarAdi + ", " + kitapTuru + ", " + yayinYili;
        kitaplarMap.put(kitapAdi, kitapValue);


        System.out.print("Kitap Ekleniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println("Kitap başarılı bir şekilde eklendi.");

        //TODO Kitap Adını, Yazar Adını, Kitap Türünü ve Yayınlanma Yılını Kullanıcıdan alarak,
        //TODO kitaplarMap'e ekleme yapınız...

        //TODO MÜMKÜNSE, kitap türünü, Enum olarak tanımlanan KitapTuru sınıfını kullanarak alınız...
        //KİTAP TÜRLERİ >>     TARIH, POLISIYE, KURGU, ROMAN, DESTAN, TANIMLANMAMIS_TUR
        //TODO Kullanıcı kitap türünü yanlış girdiği sürece , kullanıcıya
        //TODO "Hatali giris! Lutfen kitap turunu tekrar giriniz: " mesajı verin...
        //TODO while ve try-catch kullanılabilir... Giriş başarılı olursa try-catch bloğu kırılarak konsoldan
        //TODO yayınlama yılı alıp kitap ekleme işlemine devam edilebilir...
        //Kullanıcı tarafından girilen stringi, KitapTuru sınıfında tanımlanan türlerden birine çevirmeniz gerkecek...
        // kitapTuru değişkeni artık geçerli bir değere sahipse...
        // Diğer işlemlere devam edebilirsiniz.
        //System.out.println("Yayinlanma Tarihi: ");
        //String yayinTarihi = scan.nextLine();

        //TODO Ekleme işlemini tamamlayın...


    }

    public static void turVeyaYayinTarihiIleKitapBulma() throws InterruptedException {

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        boolean dogruCalisti = true;
        do {
            try {

        System.out.println("Istediginiz kitabin turunu veya yayın yılını yaziniz: ");
        System.out.println("(Tarih, Polisiye, Kurgu, Roman, Destan, Tanimlanmamis_Tur)");
        //TODO Metodu kullanıcıdan alacağınız girdileri kullanarak tamamlayın...
        String araTur = scan.nextLine().toLowerCase();

        Set<Map.Entry<String, String>> kitaplarEntrySet = kitaplarMap.entrySet();
        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");
        for ( Map.Entry<String, String> m : kitaplarEntrySet){
            String [] tumValue = m.getValue().split(", ");
            if (tumValue[1].toLowerCase().contains(araTur)||tumValue[2].toLowerCase().contains(araTur))
                System.out.println(m.getKey()+" : "+m.getValue());

        }
                dogruCalisti = true;
            }catch (Exception e){

                dogruCalisti = false;
                System.out.println("Beklenmeyen bir hata ile karşılaşıldı.");
            }
        }while (!dogruCalisti);

    }


    public static void yazardanKitapBulma() throws InterruptedException {
        boolean dogruCalisti = true;
        do {
            try {

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        System.out.println("Istediginiz yazar ismini yaziniz: ");
        //TODO Metodu kullanıcıdan alacağınız girdileri kullanarak tamamlayın...
        String araYazar = scan.nextLine().toLowerCase();
        Set<Map.Entry<String, String>> kitaplarEntrySet = kitaplarMap.entrySet();

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");
        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir.
        // Şart değil, isteğe bağlı.
        for ( Map.Entry<String, String> m : kitaplarEntrySet){
            String [] tumValue = m.getValue().split(", ");
            if (tumValue[0].toLowerCase().contains(araYazar))
                System.out.println(m.getKey()+" : "+m.getValue());

        }
                dogruCalisti = true;
            }catch (Exception e){

                dogruCalisti = false;
                System.out.println("Beklenmeyen bir hata ile karşılaşıldı.");
            }
        }while (!dogruCalisti);

    }

    public static void kitapListesiYazdir() throws InterruptedException { //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        Set<Map.Entry<String, String>> kitaplarEntrySet = kitaplarMap.entrySet();

        System.out.print("Kitap Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

        for (Map.Entry<String, String> each : kitaplarEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
        //TODO Kitapları listeleyecek metodu oluşturun...
        //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...
    }
}
