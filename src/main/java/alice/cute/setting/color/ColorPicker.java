package alice.cute.setting.color;

import java.awt.Color;

import alice.cute.setting.Setting;
import alice.cute.setting.SubSetting;
import alice.cute.setting.checkbox.Checkbox;
import alice.cute.setting.keybind.Keybind;
import alice.cute.setting.mode.Mode;
import alice.cute.setting.slider.Slider;

public class ColorPicker extends SubSetting
{
    private Color _color;
    private boolean _opened;

    public ColorPicker(Setting parent, String name, Color color) {
        this._name = name;
        this._parent = parent;
        this._color = color;
        this._opened = false;

        if (parent instanceof Checkbox) 
        {
            Checkbox p = (Checkbox) parent;
            p.addSub(this);
        }

        else if (parent instanceof Mode) 
        {
            Mode p = (Mode) parent;
            p.addSub(this);
        }

        else if (parent instanceof Slider) 
        {
            Slider p = (Slider) parent;
            p.addSub(this);
        }

        else if (parent instanceof Keybind) 
        {
            Keybind p = (Keybind) parent;
            p.addSub(this);
        }
    }

    public Color getColor() 
    {
        return this._color;
    }

    public int getRed() 
    {
        return this._color.getRed();
    }

    public int getGreen() 
    {
        return this._color.getGreen();
    }

    public int getBlue() 
    {
        return this._color.getBlue();
    }

    public int getAlpha() 
    {
        return this._color.getAlpha();
    }

    public void setColor(Color color) 
    {
        this._color = color;
    }

    public void toggleState() 
    {
        this._opened = !this._opened;
    }

    public boolean isOpened() 
    {
        return this._opened;
    }
}
