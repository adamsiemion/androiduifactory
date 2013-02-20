Android UI Factory
================

__Android UI Factory__ simplifies building of Android UI by reliving you from having to write boilerplate Android UI code. 

Instead of writing code like:

		final Button buttonCancel = new Button(this);
		buttonCancel.setText("Cancel");
		buttonCancel.setId(300);
		final RelativeLayout.LayoutParams buttonCancelParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		buttonCancelParams.topMargin = 20;
		buttonCancelParams.addRule(RelativeLayout.BELOW, buttonOk.getId());
		buttonCancelParams.addRule(RelativeLayout.ALIGN_RIGHT, checkbox2.getId());
		buttonCancelParams.addRule(RelativeLayout.ALIGN_LEFT, checkbox2.getId());
		buttonCancel.setLayoutParams(buttonCancelParams);
		layout.addView(buttonCancel);	
		
With Android UI Factory	the above can be shorten to:

		final Button buttonCancel = layoutFactory.newTextView(new Button(this))
				.setText("Cancel")
				.topMargin(20)
				.below(buttonOk)
				.alignLeft(checkbox2)
				.alignRight(checkbox2)
				.addViewToLayout()
				.view();								
								
# Library source code
				
The whole library is actually one file: [AndroidUIFactory.java](https://github.com/adko-pl/androiduifactory/blob/master/src/com/adko/androiduifactory/AndroidUIFactory.java)

Just copy it to your project.	

# Example

Below is the full source code of an Android application that is using __Android UI Factory__.

    package com.adko.androiduifactory;

    import android.app.Activity;
    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.RelativeLayout;

    public class MainActivity extends Activity {

    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
		
    		final AndroidUIFactory factory = new AndroidUIFactory(this);
		
    		final RelativeLayoutFactory layoutFactory = factory.newRelativeLayout()
    				.layoutSize(RelativeLayoutFactory.Size.MATCH_PARENT, RelativeLayoutFactory.Size.MATCH_PARENT)
    				.align(RelativeLayoutFactory.Align.ALIGN_PARENT_TOP, RelativeLayoutFactory.Align.ALIGN_PARENT_LEFT)
    				.viewSize(RelativeLayoutFactory.Size.WRAP_CONTENT, RelativeLayoutFactory.Size.WRAP_CONTENT);
    		final RelativeLayout layout = layoutFactory.layout();
		
    		final CheckBox checkbox1 = layoutFactory.newTextView(new CheckBox(this))
    				.setText("Checkbox 1")
    				.topMargin(30)
    				.leftMargin(30)
    				.addViewToLayout()
    				.view();

    		final CheckBox checkbox2 = new CheckBox(this);
    		checkbox2.setText("Checkbox 2");
    		checkbox2.setId(200);
    		final RelativeLayout.LayoutParams checkBox2params = new RelativeLayout.LayoutParams(
    				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    		checkBox2params.addRule(RelativeLayout.BELOW, checkbox1.getId());
    		checkBox2params.addRule(RelativeLayout.ALIGN_LEFT, checkbox1.getId());
    		checkbox2.setLayoutParams(checkBox2params);
    		layout.addView(checkbox2);

    		final Button buttonOk = layoutFactory.newTextView(new Button(this))
    				.setText("OK")
    				.topMargin(20)
    				.below(checkbox2)
    				.alignLeft(checkbox2)
    				.alignRight(checkbox2)
    				.addViewToLayout()
    				.view();
		
    		final Button buttonCancel = new Button(this);
    		buttonCancel.setText("Cancel");
    		buttonCancel.setId(300);
    		final RelativeLayout.LayoutParams buttonCancelParams = new RelativeLayout.LayoutParams(
    				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    		buttonCancelParams.topMargin = 20;
    		buttonCancelParams.addRule(RelativeLayout.BELOW, buttonOk.getId());
    		buttonCancelParams.addRule(RelativeLayout.ALIGN_RIGHT, checkbox2.getId());
    		buttonCancelParams.addRule(RelativeLayout.ALIGN_LEFT, checkbox2.getId());
    		buttonCancel.setLayoutParams(buttonCancelParams);
    		layout.addView(buttonCancel);	
								
    		setContentView(layout);
    	}

    }

# License

Written by Adam Siemion.

Copyright (c) 2013 Adam Siemion.

Released under the terms of the MIT License. For further information, please see the file MIT-LICENSE.