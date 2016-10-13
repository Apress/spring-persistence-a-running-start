package com.smartpants.artwork.jcr;

import org.springmodules.jcr.JcrTemplate;
import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrConstants;
import org.springmodules.jcr.support.JcrDaoSupport;
import org.apache.jackrabbit.value.StringValue;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.NodeIterator;
import javax.jcr.query.QueryResult;

import com.smartpants.artwork.domain.ArtEntity;
import com.smartpants.artwork.domain.Comment;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: Dec 2, 2008
 * Time: 11:46:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class JcrArtEntityDao extends JcrDaoSupport {


  public Node saveArtEntity(final ArtEntity artEntity){
    Node artNode = (Node) this.getTemplate()
         .execute(new JcrCallback() {
    public Object doInJcr(Session session)
        throws RepositoryException {
        // create instance of jcrConstants
        JcrConstants jcrConstants =
         new JcrConstants(session);
      // get access to the root node
      Node root = session.getRootNode();
        Node galleryNode = null;
      //check to see if our gallery:galleryImages
      // node is already created; if not, create it
      if (root.hasNode("gallery:galleryImages")) {
        galleryNode.getNode("gallery:galleryImages");
      } else {
          galleryNode =
              root.addNode("gallery:galleryImages");
      }
      // we use the artEntity title field for our
      // node name. Make sure it's JCR safe.
      String artEntityNodeName =
          (artEntity.getSafeTitle());
      Node artEntityNode = null;
      // check to see if we've already created a
      // node for this artEntity. If so, load it,
      // otherwise create it.
      if (galleryNode.hasNode(artEntityNodeName)) {
        artEntityNode =
           galleryNode.getNode(artEntityNodeName);
      } else {
          // create a new node, using our safe-title
          // as the name, and a node type of
          // gallery:artentity
          artEntityNode =
             galleryNode.addNode(
                artEntityNodeName,
                "gallery:artentity"
             );
          // we want our artentity nodes to be
          // versionable, so we need to add the
          // mixin:version
          artEntityNode.
             addMixin(jcrConstants.getMIX_VERSIONABLE());
      }
      // since this is a versionable node, we need
      // to checkout before changing properties
      artEntityNode.checkout();
      artEntityNode.setProperty(
         "title", artEntity.getTitle());
      artEntityNode.setProperty(
         "subTitle", artEntity.getSubTitle());
      artEntityNode.setProperty(
         "caption", artEntity.getCaption());
      artEntityNode.setProperty(
         "description", artEntity.getDescription());
      artEntityNode.setProperty(
         "displayDate", artEntity.getDisplayDate());
      artEntityNode.setProperty(
        "width", artEntity.getWidth());
      artEntityNode.setProperty(
        "height", artEntity.getHeight());
      // iterate through each comment in our
      // artEntity domain class
      for (Comment comment : artEntity.getComments()){
        // create a new comment node with name comment
        // Since we're defining multiple child nodes
        // with the same name, they will need to be
        // referenced by index, such as: comment[1]

        // NOTE: We should also have logic to load and
        // edit existing comments, but for the sake of
        // brevity, we have simplied this example.
        // Please check-out the full source-code
        // on-line at the book's website.
        Node commentNode =
          artEntityNode.addNode(
                  "comment", "gallery:coment"
          );
        commentNode.setProperty(
          "comment", comment.getComment());
        Calendar commentCal = Calendar.getInstance();
        commentCal.setTime(comment.getCommentDate());
        commentNode.setProperty(
          "commentDate", commentCal);
        commentNode.setProperty(
          "firstName", comment.getFirstName());
        commentNode.setProperty(
          "lastName", comment.getLastName());
      }
      // Create or load our thumbnail child node
      // which is of type nt:resource
      Node resourceNode = null;
      if (artEntityNode.hasNode("gallery:thumbnail")) {
          resourceNode =
            artEntityNode.getNode("gallery:thumbnail");
      } else {
        resourceNode =
            artEntityNode.addNode(
              "gallery:thumbnail",
              jcrConstants.getNT_RESOURCE()
            );
      }
      resourceNode.setProperty(
        jcrConstants.getJCR_DATA(),
        new ByteArrayInputStream(
          artEntity.getGalleryPicture()
            .getPicture()));
      resourceNode.setProperty(
        jcrConstants.getJCR_MIMETYPE(),
        "application/octet-stream");
      resourceNode.setProperty(
        jcrConstants.getJCR_ENCODING(), "");
      resourceNode.setProperty(
        jcrConstants.getJCR_LASTMODIFIED(),
      Calendar.getInstance());
      artEntityNode.checkin();
      session.save();
      return artEntityNode;
    }
  });
  return artNode;
}




/*
    public Node saveArtEntity(final ArtEntity artEntity) {
        Node artNode = (Node) this.getJcrTemplate().execute(new JcrCallback() {
           public Object doInJcr(Session session) throws RepositoryException {
               JcrConstants jcrConstants = new JcrConstants(session);
                Node root = session.getRootNode();
                Node galleryNode = root.getNode("galleryImages");
                Node newArtEntityNode = galleryNode.addNode("artEntity");
               newArtEntityNode.checkout();
               newArtEntityNode.addMixin(jcrConstants.getMIX_VERSIONABLE());
               newArtEntityNode.addMixin(jcrConstants.getMIX_REFERENCEABLE());
                newArtEntityNode.setProperty("title", artEntity.getTitle());
                newArtEntityNode.setProperty("subTitle", artEntity.getSubTitle());
               newArtEntityNode.setProperty("caption", artEntity.getCaption());
               newArtEntityNode.setProperty("description", artEntity.getDescription());
               newArtEntityNode.setProperty("displayDate", artEntity.getDisplayDate());
               newArtEntityNode.setProperty("width", artEntity.getWidth());
               newArtEntityNode.setProperty("height", artEntity.getHeight());
               for (Comment comment : artEntity.getComments()) {
                   Node commentNode = newArtEntityNode.addNode("comment");
                   commentNode.setProperty("comment", comment.getComment());
                   Calendar commentCal = Calendar.getInstance();
                   commentCal.setTime(comment.getCommentDate());
                   commentNode.setProperty("commentDate", commentCal);
                   commentNode.setProperty("firstName", comment.getFirstName());
                   commentNode.setProperty("lastName", comment.getLastName());
               }

               Node imageNode = newArtEntityNode.addNode("galleryImage", jcrConstants.getNT_FILE());
               Node resourceNode = imageNode.addNode("jcr:content", jcrConstants.getNT_RESOURCE());
               resourceNode.setProperty(jcrConstants.getJCR_DATA(), new ByteArrayInputStream(artEntity.getGalleryPicture().getPicture()));
               resourceNode.setProperty(jcrConstants.getJCR_MIMETYPE(), "application/octet-stream");
               resourceNode.setProperty(jcrConstants.getJCR_ENCODING(), "");
               resourceNode.setProperty(jcrConstants.getJCR_LASTMODIFIED(), Calendar.getInstance());
                newArtEntityNode.checkin();
                session.save();
                return newArtEntityNode;
            }

        });

       return artNode;

    }*/


    public List findArtEntityNodesBySubtitle(String subtitle) {
        // define an XPath query, using the gallery:subtitle
        // as a constraint
        QueryResult result = this.getTemplate().
                query("//*[@gallery:subtitle = " + subtitle + "]");
        List foundArtEntities = new ArrayList();
        try {
            // get a NodeIterator from the returned QueryResult
            NodeIterator nodeIt = result.getNodes();
            while (nodeIt.hasNext()) {
                Node curNode = (Node) nodeIt.next();
                // Here we convert the returned node back
                // to an ArtEntity instance (method not shown)
                ArtEntity artEntity = new ArtEntity();
                //ArtEntity artEntity = nodeToArtEntity(curNode);
                // add the converted ArtEntity to our List
                foundArtEntities.add(artEntity);
            }
        } catch (RepositoryException e) {
            // Here we convert any Jcr
            // RepositoryException into Spring's
            // unchecked DataAccessException hierarchy
            throw this.convertJcrAccessException(e);
        }
        return foundArtEntities;
    }






}
