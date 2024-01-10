package services;


import dao.ImageDAO;
import models.Image;
import models.Produit;

import java.util.List;


public class ImageService {

    private ImageDAO imageDAO;

    public ImageService() { imageDAO = new ImageDAO(); }


    public void create(String url, Produit produit) {
        Image newImg = new Image();
        newImg.setProduit(produit);
        newImg.setUrl(url);

        imageDAO.create(newImg);
    }

    public void update(Image img) {imageDAO.update(img);}

    public void del(Long id) { imageDAO.remove(id);}

    public List<Image> getByProdctId(Long id) {
        return imageDAO.getByProdctId(id);
    }


}
