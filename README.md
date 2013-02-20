androiduifactory
================

Simplifies building of Android UI by reliving you from having to write boilerplate Android UI code. 

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
								
								
								