package modernartui.project.modern_art_ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;


public class ModernArtUIActivity extends AppCompatActivity {
    private static SeekBar skbr_colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art_ui);
        changeColorSeekBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_modern_art_ui, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            MoreInformationDialogFragment mMoreInfo = new MoreInformationDialogFragment();
            mMoreInfo.show(getFragmentManager(), "Dialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeColorSeekBar() {

        skbr_colors = (SeekBar) findViewById(R.id.seekBar);

        final LinearLayout blueRectangle1 = (LinearLayout) findViewById(R.id.rectangle1);
        final LinearLayout redRectangle = (LinearLayout) findViewById(R.id.rectangle2);
        final LinearLayout greenRectangle5 = (LinearLayout) findViewById(R.id.rectangle5);
        final LinearLayout yellowRectangle = (LinearLayout) findViewById(R.id.rectangle6);
        final int blue_color_value = ContextCompat.getColor(getApplicationContext(), R.color.blue);
        final int red_color_value = ContextCompat.getColor(getApplicationContext(), R.color.red);
        final int yellow_color_value = ContextCompat.getColor(getApplicationContext(), R.color.yellow);
        final int green_color_value = ContextCompat.getColor(getApplicationContext(), R.color.green);

        skbr_colors.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressValue = 0;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress;
                        setProgressBasedBackgroundColor(redRectangle, red_color_value);
                        setProgressBasedBackgroundColor(blueRectangle1, blue_color_value);
                        setProgressBasedBackgroundColor(yellowRectangle, yellow_color_value);
                        setProgressBasedBackgroundColor(greenRectangle5, green_color_value);
                    }

                    private void setProgressBasedBackgroundColor(LinearLayout rectangle, int OriginalBoxColor) {
                        float[] hsvColor = new float[3];
                        Color.colorToHSV(OriginalBoxColor, hsvColor);
                        hsvColor[0] = hsvColor[0] + progressValue;
                        hsvColor[0] = hsvColor[0] % 360;
                        rectangle.setBackgroundColor(Color.HSVToColor(Color.alpha(OriginalBoxColor), hsvColor));
                    }

                    //In this case these methods are not necessary
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) { }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) { }
                }
        );
    }
}
