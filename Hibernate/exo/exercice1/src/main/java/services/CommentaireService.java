package services;

import dao.CommentaireDAO;
import models.Commentaire;
import models.Produit;

import java.time.LocalDate;
import java.util.List;

public class CommentaireService {

    private CommentaireDAO commentaireDAO;

    public CommentaireService() {commentaireDAO = new CommentaireDAO();}

    public void create(String avis, LocalDate date, int note, Produit produit) {

        if (note < 0) {note = 0;}
        if (note > 5) { note = 5;}

        Commentaire newCom = new Commentaire();
        newCom.setAvis(avis);
        newCom.setDate(date);
        newCom.setNote(note);
        newCom.setProduit(produit);

        commentaireDAO.create(newCom);

    };

    public void update(Commentaire commentaire) {
        commentaireDAO.update(commentaire);
    }

    public void del(Long id) {
        commentaireDAO.remove(id);
    }

    public List<Commentaire> getByProdctId(Long idProdct){
        return commentaireDAO.getByProdctId(idProdct);
    }


}
