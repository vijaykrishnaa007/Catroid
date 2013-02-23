/**
 *  Catroid: An on-device visual programming system for Android devices
 *  Copyright (C) 2010-2013 The Catrobat Team
 *  (<http://developer.catrobat.org/credits>)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://developer.catrobat.org/license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.content.bricks;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.ui.fragment.FormulaEditorFragment;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeYByNBrick implements Brick, OnClickListener {
	private static final long serialVersionUID = 1L;
	private Formula yMovement;
	private Sprite sprite;

	private transient View view;

	public ChangeYByNBrick() {

	}

	public ChangeYByNBrick(Sprite sprite, int yMovementValue) {
		this.sprite = sprite;

		yMovement = new Formula(Integer.toString(yMovementValue));
	}

	public ChangeYByNBrick(Sprite sprite, Formula yMovement) {
		this.sprite = sprite;

		this.yMovement = yMovement;
	}

	@Override
	public int getRequiredResources() {
		return NO_RESOURCES;
	}

	@Override
	public void execute() {
		int yMovementValue = yMovement.interpretInteger();

		sprite.look.aquireXYWidthHeightLock();
		int yPosition = (int) sprite.look.getYPosition();

		if (yPosition > 0 && yMovementValue > 0 && yPosition + yMovementValue < 0) {
			yPosition = Integer.MAX_VALUE;
		} else if (yPosition < 0 && yMovementValue < 0 && yPosition + yMovementValue > 0) {
			yPosition = Integer.MIN_VALUE;
		} else {
			yPosition += yMovementValue;
		}

		sprite.look.setXYPosition(sprite.look.getXPosition(), yPosition);
		sprite.look.releaseXYWidthHeightLock();
	}

	@Override
	public Sprite getSprite() {
		return this.sprite;
	}

	@Override
	public View getView(Context context, int brickId, BaseAdapter adapter) {

		view = View.inflate(context, R.layout.brick_change_y, null);

		TextView textY = (TextView) view.findViewById(R.id.brick_change_y_prototype_text_view);
		EditText editY = (EditText) view.findViewById(R.id.brick_change_y_edit_text);
		yMovement.setTextFieldId(R.id.brick_change_y_edit_text);
		yMovement.refreshTextField(view);

		textY.setVisibility(View.GONE);
		editY.setVisibility(View.VISIBLE);
		editY.setOnClickListener(this);

		return view;
	}

	@Override
	public View getPrototypeView(Context context) {
		return View.inflate(context, R.layout.brick_change_y, null);
	}

	@Override
	public Brick clone() {
		return new ChangeYByNBrick(getSprite(), yMovement);
	}

	@Override
	public void onClick(View view) {
		FormulaEditorFragment.showFragment(view, this, yMovement);
	}
}
