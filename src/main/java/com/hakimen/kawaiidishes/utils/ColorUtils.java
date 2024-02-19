package com.hakimen.kawaiidishes.utils;

public class ColorUtils {
    public static float[] getColorsFromHex(int hexvalue){
        String hex = Integer.toHexString(hexvalue );
        hex += "0".repeat(6 - (hex.length()-1));
        int resultRed = Integer.valueOf(hex.substring(0, 2), 16);
        int resultGreen = Integer.valueOf(hex.substring(2, 4), 16);
        int resultBlue = Integer.valueOf(hex.substring(4, 6), 16);

        float r = resultRed / 255f;
        float g = resultGreen / 255f;
        float b = resultBlue / 255f;

        return new float[]{r, g, b};
    }

    public static float[] rgbToHsl(int rgb) {
        float[] hsl = new float[3];
        float r = ((0x00ff0000 & rgb) >> 16) / 255.f;
        float g = ((0x0000ff00 & rgb) >> 8) / 255.f;
        float b = ((0x000000ff & rgb)) / 255.f;
        float max = Math.max(Math.max(r, g), b);
        float min = Math.min(Math.min(r, g), b);
        float c = max - min;

        float h_ = 0.f;
        if (c == 0) {
            h_ = 0;
        } else if (max == r) {
            h_ = (g-b) / c;
            if (h_ < 0) h_ += 6.f;
        } else if (max == g) {
            h_ = (b-r) / c + 2.f;
        } else if (max == b) {
            h_ = (r-g) / c + 4.f;
        }
        float h = 60.f * h_;

        float l = (max + min) * 0.5f;

        float s;
        if (c == 0) {
            s = 0.f;
        } else {
            s = c / (1 - Math.abs(2.f * l - 1.f));
        }

        hsl[0] = h;
        hsl[1] = s;
        hsl[2] = l;
        return hsl;
    }

    public static int hslToRgb(float[] hsl) {
        float h = hsl[0];
        float s = hsl[1];
        float l = hsl[2];

        float c = (1 - Math.abs(2.f * l - 1.f)) * s;
        float h_ = h / 60.f;
        float h_mod2 = h_;
        if (h_mod2 >= 4.f) h_mod2 -= 4.f;
        else if (h_mod2 >= 2.f) h_mod2 -= 2.f;

        float x = c * (1 - Math.abs(h_mod2 - 1));
        float r_, g_, b_;
        if (h_ < 1)      { r_ = c; g_ = x; b_ = 0; }
        else if (h_ < 2) { r_ = x; g_ = c; b_ = 0; }
        else if (h_ < 3) { r_ = 0; g_ = c; b_ = x; }
        else if (h_ < 4) { r_ = 0; g_ = x; b_ = c; }
        else if (h_ < 5) { r_ = x; g_ = 0; b_ = c; }
        else             { r_ = c; g_ = 0; b_ = x; }

        float m = l - (0.5f * c);
        int r = (int)((r_ + m) * (255.f) + 0.5f);
        int g = (int)((g_ + m) * (255.f) + 0.5f);
        int b = (int)((b_ + m) * (255.f) + 0.5f);
        return r << 16 | g << 8 | b;
    }
}
