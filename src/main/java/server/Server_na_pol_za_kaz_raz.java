package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Ta klasa implementuje Socket server
 *
 */

/**
 * MICHALE
 * todo:
 * Przeanalizuj ServerMenagment i pozwól na 2 połączenia na ruch- pozwol nam grać.
 *
 */


public class Server_na_pol_za_kaz_raz {
        private static ServerSocket server;
        /*port socket serwer-a*/
        private static int port = 6666;




        public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
                /*tworzenie socket serwer*/
                server = new ServerSocket(port);
                int a1=20;
                int b1=20;
                int a2=20;
                int b2=20;
                boolean first =true;
                System.out.println("Stworzylem server");
                /*Tutaj daje wiadomosc klientom ktory byl pierwszy*/

                //******************************//
                //---- UNDER CONSTRUCKTION------//
                //******************************//

                /*Czekam na klienta 1*/
                System.out.println("Czekam na 1 gracza");
                Socket socketP1 = server.accept();
                System.out.println("Gracz 1 dolaczyl do serwera");
                /*Daje klientowi 1 odpowiedz*/
                ObjectOutputStream oosP1 = new ObjectOutputStream(socketP1.getOutputStream());
                oosP1.writeObject(true);

                /*Teraz biore wiadomosc od klienta 1 */
                ObjectInputStream checkera = new ObjectInputStream(socketP1.getInputStream());


                /*Czekam na klienta 2*/
                System.out.println("Czekam na 2 gracza");
                Socket socketP2 = server.accept();
                System.out.println("Gracz 2 dolaczyl do serwera");

                /*Konwertuje na inta*/
                String a = (String) checkera.readObject();
                System.out.println("Dostalem waidomosc od 1 gracza: " + a);

                /*Daje klientowi 2 odpowiedz*/
                ObjectOutputStream oosP2 = new ObjectOutputStream(socketP2.getOutputStream());
                oosP2.writeObject(false);

                /*Teraz biore wiadomosc od klienta 2 */
                ObjectInputStream checkerb = new ObjectInputStream(socketP2.getInputStream());

                /*Konwertuje na inta*/
                String b = (String) checkerb.readObject();
                System.out.println("Dostalem widomosc od 2 gracza: " + b);



                /*informuje klienta 1 ze wszyscy sa*/
                oosP2.writeObject(true);

                /*informuje klienta 2 ze wszyscy sa*/
                oosP1.writeObject(true);




                checkera.close();
                checkerb.close();
                oosP1.close();
                socketP1.close();
                oosP2.close();
                socketP2.close();

                while(true){
                        /**
                         *
                         * Lacze sie z kientem nr 1
                         *
                         * */

                        /*Czekam na klienta 1*/
                        System.out.println("Czekam na 1 gracza");
                        Socket socket1a = server.accept();
                        System.out.println("Gracz 1 dolaczyl do serwera");

                        /*Daje klientowi 1 odpowiedz*/
                        ObjectOutputStream oos1 = new ObjectOutputStream(socket1a.getOutputStream());
                        oos1.writeObject(a2);
                        oos1.writeObject(b2);

                        /*zamykam wszystkie zrodla*/
                        oos1.close();
                        socket1a.close();
                        /**
                         *
                         * Lacze sie z kientem nr 2
                         *
                         * */


                        /*Czekam na klienta 2*/
                        System.out.println("Czekam na 2 gracza po raz 2 ");
                        Socket socket2b = server.accept();
                        System.out.println("Gracz 2 dolaczyl do serwera po raz 2");

                        /*Daje klientowi 2 odpowiedz*/
                        ObjectOutputStream oos2 = new ObjectOutputStream(socket2b.getOutputStream());
                        oos2.writeObject(a1);
                        oos2.writeObject(b1);

                        /*Konce obcowanie z klientem drugim*/
                        /*zamykam wszystkie zrodla*/
                        socket2b.close();
                        oos2.close();



                        /*Czekam na klienta 1*/
                        System.out.println("Czekam na 1 gracza po raz 2");
                        Socket socket1b = server.accept();
                        System.out.println("Gracz 1 dolaczyl do serwera po raz 2");

                        /*Teraz biore wiadomosc od klienta 1 */
                        ObjectInputStream checker1 = new ObjectInputStream(socket1b.getInputStream());

                        /*Konwertuje na inta*/
                        a1 = (int) checker1.readObject();
                        System.out.println("Dostalem widomosc od 1 gracza: " + a1);
                        /*Konwertuje na inta*/
                        b1 = (int) checker1.readObject();
                        System.out.println("Dostalem widomosc od 1 gracza: " + b1);



                        /*Konce obcowanie z klientem pierwszym*/
                        /*zamykam wszystkie zrodla*/
                        checker1.close();
                        socket1b.close();

                        /*Czekam na klienta 2*/
                        System.out.println("Czekam na 2 gracza");
                        Socket socket2a = server.accept();
                        System.out.println("Gracz 2 dolaczyl do serwera");

                        /*Teraz biore wiadomosc od klienta 2*/
                        ObjectInputStream checker2 = new ObjectInputStream(socket2a.getInputStream());

                        /*Konwertuje na inta*/
                        a2 = (int) checker2.readObject();
                        System.out.println("Dostalem widomosc od 2 gracza: " + a2);
                        b2 = (int) checker2.readObject();
                        System.out.println("Dostalem widomosc od 2 gracza: " + b2);

                        /*zamykam wszystkie zrodla*/
                        checker2.close();
                        socket2a.close();




                        /*Sprawdzam czy to juz koniec naszej zabawy*/
                        if(a1==20 && a2==20 && b1==20 && b2==20) break;



                }

                /******************************************/
                /*Otwieram czat do rozmowy miedzy graczami*/
                /******************************************/

                /*Czekam na klienta 1*/
                System.out.println("Czekam na 1 gracza");
                Socket sockettP1 = server.accept();
                System.out.println("Gracz 1 dolaczyl do serwera");

                /*Czekam na klienta 2*/
                System.out.println("Czekam na 2 gracza");
                Socket sockettP2 = server.accept();
                System.out.println("Gracz 2 dolaczyl do serwera");

                String mes1,mes2;
                while(true){

                        /*Teraz biore wiadomosc od klienta 2*/
                        ObjectInputStream checker2 = new ObjectInputStream(sockettP2.getInputStream());

                        /*Konwertuje na String*/
                        mes2 = (String) checker2.readObject();
                        System.out.println("Dostalem widomosc od 2 gracza: " + mes2);


                        /*Daje klientowi 1 odpowiedz*/
                        ObjectOutputStream oos1 = new ObjectOutputStream(sockettP1.getOutputStream());
                        oos1.writeObject(mes2);


                        /*Teraz biore wiadomosc od klienta 1*/
                        ObjectInputStream checker1 = new ObjectInputStream(sockettP2.getInputStream());

                        /*Konwertuje na String*/
                        mes1 = (String) checker1.readObject();
                        System.out.println("Dostalem widomosc od 1 gracza: " + mes1);

                        /*Daje klientowi 2 odpowiedz*/
                        ObjectOutputStream oos2 = new ObjectOutputStream(sockettP2.getOutputStream());
                        oos2.writeObject(mes1);

                        if((mes1=="WIN" && mes2 =="LOSE") || (mes1=="LOSE" && mes2=="WIN")) break;
                }
                 System.out.println("Shutting down Socket server!!");
                //zamknij ServerSocket object
                server.close();
        }

}
