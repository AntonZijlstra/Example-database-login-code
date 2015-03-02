# Image-handler-module-code
The code containing the image handler.

For an example of it's use please run alonside the example layout xml file provided having placed two images called test 1 and 2 in your drawables folder.

Otherwise the Image handler module is designed to be fully modular and therefore can be run in any code provided a valid Image and ImageView frame is provided.

In order to use the image handler you need to do the following:
Step 1: Define the Strings for the Image file and imageView frame names
Step 2: Use getResources to obtain the ID's of the Image and frame
Step 3: Call the embed_image module with those arguments
E.g.
  Step 1:
  String imageToEmbed = "test1";
  String frameToEmbedImage = "imageView1";

  Step 2:
  int imageID = getResources().getIdentifier(imageToEmbed, "drawable", "com.claritas.anton.socbox");
  int frameID = getResources().getIdentifier(frameToEmbedImage, "id", "com.claritas.anton.socbox");

  Step 3
  embed_image(imageID, frameID);
  
  If you have any questions regarding this please contact me.
  
  Anton

