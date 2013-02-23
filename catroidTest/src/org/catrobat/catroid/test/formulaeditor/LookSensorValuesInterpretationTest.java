/**
 *  Catroid: An on-device graphical programming language for Android devices
 *  Copyright (C) 2010-2011 The Catroid Team
 *  (<http://code.google.com/p/catroid/wiki/Credits>)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://www.catroid.org/catroid_license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *   
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.test.formulaeditor;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.formulaeditor.Sensors;

import android.test.AndroidTestCase;

public class LookSensorValuesInterpretationTest extends AndroidTestCase {

	private static final float LOOK_ALPHA = 0.5f;
	private static final float LOOK_Y_POSITION = 23.4f;
	private static final float LOOK_X_POSITION = 5.6f;
	private static final float LOOK_BRIGHTNESS = 0.7f;
	private static final float LOOK_SCALE = 90.3f;
	private static final float LOOK_ROTATION = 30.7f;
	private static final int LOOK_ZPOSITION = 3;
	private static final float DELTA = 0.01f;

	@Override
	protected void setUp() {
		Sprite testSprite = new Sprite("sprite");
		ProjectManager.getInstance().setCurrentSprite(testSprite);

		testSprite.look.setXPosition(LOOK_X_POSITION);
		testSprite.look.setYPosition(LOOK_Y_POSITION);
		testSprite.look.setAlphaValue(LOOK_ALPHA);
		testSprite.look.setBrightnessValue(LOOK_BRIGHTNESS);
		testSprite.look.scaleX = LOOK_SCALE;
		testSprite.look.scaleY = LOOK_SCALE;
		testSprite.look.rotation = LOOK_ROTATION;
		testSprite.look.zPosition = LOOK_ZPOSITION;
	}

	public void testLookSensorValues() {

		Formula lookXPositionFormula = new Formula(Sensors.LOOK_X_.sensorName);
		assertEquals("Formula interpretation is not as expected", LOOK_X_POSITION,
				lookXPositionFormula.interpretFloat(), DELTA);

		Formula lookYPositionFormula = new Formula(Sensors.LOOK_Y_.sensorName);
		assertEquals("Formula interpretation is not as expected", LOOK_Y_POSITION,
				lookYPositionFormula.interpretFloat(), DELTA);

		Formula lookAlphaValueFormula = new Formula(Sensors.LOOK_GHOSTEFFECT_.sensorName);
		assertEquals("Formula interpretation is not as expected", LOOK_ALPHA, lookAlphaValueFormula.interpretFloat(),
				DELTA);

		Formula lookBrightnessFormula = new Formula(Sensors.LOOK_BRIGHTNESS_.sensorName);
		assertEquals("Formula interpretation is not as expected", LOOK_BRIGHTNESS,
				lookBrightnessFormula.interpretFloat(), DELTA);

		Formula lookScaleFormula = new Formula(Sensors.LOOK_SIZE_.sensorName);
		assertEquals("Formula interpretation is not as expected", LOOK_SCALE, lookScaleFormula.interpretFloat(), DELTA);

		Formula lookRotateFormula = new Formula(Sensors.LOOK_ROTATION_.sensorName);
		assertEquals("Formula interpretation is not as expected", LOOK_ROTATION, lookRotateFormula.interpretFloat(),
				DELTA);

		Formula lookZPositionFormula = new Formula(Sensors.LOOK_LAYER_.sensorName);
		assertEquals("Formula interpretation is not as expected", LOOK_ZPOSITION,
				lookZPositionFormula.interpretInteger());

	}
}
