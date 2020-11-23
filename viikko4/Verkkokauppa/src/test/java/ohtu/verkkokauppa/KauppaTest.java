package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;


    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.saldo(1)).thenReturn(10); 
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);    
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
    }

    @Test
    public void pankinMetodiTilisiirtoTulostuuOikein() {
            
            when(viite.uusi()).thenReturn(42);
            
            when(varasto.saldo(1)).thenReturn(10); 
            
            when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    
            Kauppa k = new Kauppa(varasto, pankki, viite);              
    
            k.aloitaAsiointi();
            k.lisaaKoriin(1);    
            k.tilimaksu("pekka", "12345");
    
            verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455")
             , eq(5));   
    }

    @Test
    public void tilisiirtoOnnistuuKahdellaEriTuotteella() {

        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(2);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "liha", 4));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(9));


    }

    @Test
    public void tilisiirtoOnnistuuKahdellaSamallaTuotteella() {

        when(varasto.saldo(1)).thenReturn(3);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
     
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(10));


    }


    @Test
    public void koriToimiiSaldo1ja0() {

        when(varasto.saldo(1)).thenReturn(1);
       

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "peruna", 5));
     
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(5));


    }

    @Test
    public void koriPoistoToimii() {

        when(varasto.saldo(1)).thenReturn(1);
       

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        
        verify(varasto).saldo(eq(1));

    }

    @Test
    public void uusiViiteJokaiseenMaksuun() {

        when(varasto.saldo(1)).thenReturn(3);
        

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "67890");

        verify(viite, times(2)).uusi();

     
    }

    @Test
    public void uusiHintaJokaiseenOstoskoriin() {

        when(varasto.saldo(1)).thenReturn(3);
        

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(5));

     
    }

}
