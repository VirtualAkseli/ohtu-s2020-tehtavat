package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa implements VarastoI, PankkiI, ViitegeneraattoriI {
    
    @Autowired
    private Varasto varasto;
    @Autowired
    private Pankki pankki;
    private Ostoskori ostoskori;
    @Autowired
    private Viitegeneraattori viitegeneraattori;
    private String kaupanTili;
    
    

    
    
    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
        kaupanTili = "33333-44455";
    }

    public void poistaKorista(int id) {
        Tuote t = this.varasto.haeTuote(id); 
        this.varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (this.varasto.saldo(id)>0) {
            Tuote t = this.varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            this.varasto.otaVarastosta(t);
        }
    }

   
    
    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = this.viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return this.pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

    @Override
    public Tuote haeTuote(int id) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void otaVarastosta(Tuote t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void palautaVarastoon(Tuote t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saldo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int uusi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
