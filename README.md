# Android Yemek Tarifi Uygulaması

Bu uygulama Java Programlama dersi projesi için hazırlanmıştır.


#### NOT: Uygulamanın çalışması için rest api servisine ihtiyaç duymaktadır.

## Kullanılan Kütüpahaneler
- com.squareup.picasso:picasso
- com.loopj.android:android-async-http
- com.android.support:cardview-v7

## Ekran Görüntüleri
<img src="https://github.com/ramazanogunc/Android-Yemek-Tarifi/blob/master/Documentation/screenShoot/Picture1.png"
width="210"
alt="Android Yemek Tarifi 1"/>
<img src="https://github.com/ramazanogunc/Android-Yemek-Tarifi/blob/master/Documentation/screenShoot/Picture2.png"
width="210"
alt="Android Yemek Tarifi 2"/>
<img src="https://github.com/ramazanogunc/Android-Yemek-Tarifi/blob/master/Documentation/screenShoot/Picture3.png"
width="210"
alt="Android Yemek Tarifi 3"/>
<img src="https://github.com/ramazanogunc/Android-Yemek-Tarifi/blob/master/Documentation/screenShoot/Picture4.png"
width="210"
alt="Android Yemek Tarifi 4"/>
<img src="https://github.com/ramazanogunc/Android-Yemek-Tarifi/blob/master/Documentation/screenShoot/Picture5.png"
width="210"
alt="Android Yemek Tarifi 5"/>


## Activity ve Fragmentlar
- Splash Activity: Geçişli karşılama ekranı.
- Main Activity: Ana Ekran.
 - Home Fragment: Rastgele Tariflerin geldiği fragment.
 - Category Fragment: Kategorilerin yükendiği fragment.
- List Activity: Tarfilerin Listelendiği Activity.
- Detail Activity: Tarifin ve malzemelerin gösteriliddği activity.

## Varlık Sınıfları
- Category: Kategori
- Food: Yemek
- Material: Malzeme

## Asenkron ve Parser Sınıfları

Asenkron http requestinde bulunup gelen json veriyi Parser sınıfında parse işlemini yaptırıp collection elde eder. Daha sonra listeleme işlemini gerçekleştiri.

- FoodAsnc
- CategoryAsync
- FoodParser
- CategroyParser


## License
[APACHE](http://www.apache.org/licenses/LICENSE-2.0)
