package com.claritas.anton.socbox;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

//This is simply an example activity showing how to use the Image Handler. This class embeds 4 separate images in
//frames, one stationary and three scrolling ones. Step by step instructions as to how this works can be found in the
//Header for the module or in the README
public class ExampleActivity extends ActionBarActivity {

    //Initialize the Image Handler
    public ImageHandler imageHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //These are the strings for use in the Frame and Image ID's. These are the names of the files and imageView Frames
        //that the handler will use
        String imageToEmbed = "test1";
        String frameToEmbedImage = "imageView1";
        String scrollingImageToEmbed = "test1";
        String scrollingFrameToEmbedImage = "imageView3";
        String scrollingImageToEmbed2 = "test1";
        String scrollingFrameToEmbedImage2 = "imageView4";
        String scrollingImageToEmbed3 = "test1";
        String scrollingFrameToEmbedImage3 = "imageView5";

        super.onCreate(savedInstanceState);
        //Set the layout to the main activity layout
        setContentView(R.layout.activity_main);

        //Create a new Image Handler
        imageHandler = new ImageHandler();

        //Define the frame and Image IDs based upon the strings for the file and frame names (above) so that these can
        // be passed to the image handler and used to embed the given images in the given ImageViews
        int imageID = getResources().getIdentifier(imageToEmbed, "drawable", "com.claritas.anton.socbox");
        int frameID = getResources().getIdentifier(frameToEmbedImage, "id", "com.claritas.anton.socbox");
        int scrollingImageID = getResources().getIdentifier(scrollingImageToEmbed, "drawable", "com.claritas.anton.socbox");
        int scrollingFrameID = getResources().getIdentifier(scrollingFrameToEmbedImage, "id", "com.claritas.anton.socbox");
        int scrollingImageID2 = getResources().getIdentifier(scrollingImageToEmbed2, "drawable", "com.claritas.anton.socbox");
        int scrollingFrameID2 = getResources().getIdentifier(scrollingFrameToEmbedImage2, "id", "com.claritas.anton.socbox");
        int scrollingImageID3 = getResources().getIdentifier(scrollingImageToEmbed3, "drawable", "com.claritas.anton.socbox");
        int scrollingFrameID3 = getResources().getIdentifier(scrollingFrameToEmbedImage3, "id", "com.claritas.anton.socbox");

        //Embed a single image in a frame
        imageHandler.embed_image(this, imageID, frameID);

        //Embed multiple images in a (scrolling) frame
        imageHandler.embed_image(this, scrollingImageID, scrollingFrameID);
        imageHandler.embed_image(this, scrollingImageID2, scrollingFrameID2);
        imageHandler.embed_image(this, scrollingImageID3, scrollingFrameID3);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
