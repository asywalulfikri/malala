package tabhost.malala;

import com.example.malala.R;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabAndListView extends TabActivity {
	// TabSpec Names
	private static final String MASAKAN_SPEC = "Masakan";
	private static final String PENGANAN_SPEC = "Penganan";
	private static final String MINUMAN_SPEC = "Minuman";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        // Masakan Tab
        TabSpec masakanSpec = tabHost.newTabSpec(MASAKAN_SPEC);
        // Tab Icon
        masakanSpec.setIndicator(MASAKAN_SPEC, getResources().getDrawable(R.drawable.ic_launcher));
        Intent masakanIntent = new Intent(this, List_masakan_grid.class);
        // Tab Content
        masakanSpec.setContent(masakanIntent);
        
        // Penganan Tab
        TabSpec pengananSpec = tabHost.newTabSpec(PENGANAN_SPEC);
        pengananSpec.setIndicator(PENGANAN_SPEC, getResources().getDrawable(R.drawable.ic_launcher));
        Intent pengananIntent = new Intent(this, List_penganan.class);
        pengananSpec.setContent(pengananIntent);
        
        // Minuman Tab
        TabSpec minumanSpec = tabHost.newTabSpec(MINUMAN_SPEC);
        minumanSpec.setIndicator(MINUMAN_SPEC, getResources().getDrawable(R.drawable.ic_launcher));
        Intent minumanIntent = new Intent(this, List_minuman_grid.class);
        minumanSpec.setContent(minumanIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(masakanSpec); 
        tabHost.addTab(pengananSpec); 
        tabHost.addTab(minumanSpec);
    }
}