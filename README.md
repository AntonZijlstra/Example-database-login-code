# Image-handler-module-code
The code containing the image handler.

For an example of it's use please run the ExampleActivity file provided having ImageHandler and activitymain in the correct folders and having placed two images called test 1 and 2 in your drawables folder. You may also need to update your Android manifest to run the ExampleActivity.

Otherwise the Image handler is designed to be fully modular and therefore can be called from any activity provided a valid Image and ImageView frame is provided.

In order to use the image handler you need to do the following:

Step 1: Define an Image handler method within your activity

Step 2: Define the Strings for the Image file and imageView frame names

Step 3: Use getResources to obtain the ID's of the Image and frame

Step 4: Call the embed_image module with those arguments

E.g.

 Step 1:
 
    public ImageHandler imageHandler;
    imageHandler = new ImageHandler();

 Step 2:
 
    String imageToEmbed = "test1";
    String frameToEmbedImage = "imageView1";
  
 Step 3:
 
    int imageID = getResources().getIdentifier(imageToEmbed, "drawable", "com.claritas.anton.socbox");
    int frameID = getResources().getIdentifier(frameToEmbedImage, "id", "com.claritas.anton.socbox");

 Step 4:
  
    imageHandler.embed_image(this, imageID, frameID);

To see an example of this please see the ExampleActivity file
  
  If you have any questions regarding this please contact me.
  
  Anton

