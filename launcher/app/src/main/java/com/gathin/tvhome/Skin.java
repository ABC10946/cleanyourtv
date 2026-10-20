package com.gathin.tvhome;

/**
 * The launcher's look, picked by a theme's {@code "skin"} key (see themes/README.md). Everything a
 * skin changes is applied in code at start, so switching is a theme push and never a rebuild:
 * {@code tools/theme.sh themes/phosphor} turns the retro look on, {@code tools/theme.sh --reset}
 * puts the built-in one back.
 *
 * A colour of 0 means "leave this alone", which is how the default skin keeps the values written in
 * activity_main.xml and the drawables.
 */
final class Skin {

    static final String DEFAULT = "default";
    static final String PHOSPHOR = "phosphor";

    /** The name as written in config.json, for logging. */
    String name = DEFAULT;
    /** Font resource for the launcher's own text, or 0 to keep @font/outfit from the layout. */
    int fontRes;
    /** 1 keeps the designed 16dp tile rounding; 0 squares every corner off. */
    float cornerScale = 1f;

    int greetingColor, clockColor, ampmColor, dateColor, dividerColor, heroColor;
    int tileFill, tileStroke, tileLabelColor;
    /** Focused tile: a skin can invert it instead of relying on the floating highlight. 0 = no change. */
    int tileFillFocused, tileStrokeFocused, tileTextFocused;
    /** Draws the app's name in the tile instead of its own banner artwork. */
    boolean textTiles;
    /** Tile text size in sp, 0 to keep the layout's; and whether to upper-case it. */
    float tileTextSp;
    boolean tileTextAllCaps;
    /** The glass slab that springs from tile to tile. Off leaves the focused tile to show focus itself. */
    boolean floatingHighlight = true;
    int pillText, pillTextFocused, pillStroke, pillFill, pillFillFocused;
    int dockBand, dockEdge;
    int glassFillTop, glassFillBottom, glassSpecular, glassRim, glassGlow;
    /** Ambient glow colour; 0 samples the focused app's own artwork, as the default look does. */
    int fixedAmbient;
    /** Tints app banners to this colour through a desaturating ColorMatrix; 0 leaves them in colour. */
    int monochrome;
    /** Draws CRT scan lines and a vignette over everything. */
    boolean scanlines;

    private Skin() {
    }

    static Skin of(String name) {
        return PHOSPHOR.equals(name) ? phosphor() : defaults();
    }

    /** The built-in tvOS style look: whatever the layout and drawables already say. */
    static Skin defaults() {
        return new Skin();
    }

    /**
     * Amber CRT monochrome: one phosphor colour, square corners, scan lines, and app names set in
     * type instead of their own artwork, so nothing breaks the single-colour illusion. The focused
     * tile inverts rather than being tracked by the floating glass highlight.
     */
    static Skin phosphor() {
        Skin s = new Skin();
        s.name = PHOSPHOR;
        s.fontRes = R.font.vt323;
        s.cornerScale = 0f;

        s.greetingColor = 0xFFC87F14;
        s.clockColor = 0xFFFFCB5E;
        s.ampmColor = 0xFFC98B2B;
        s.dateColor = 0xFFC98B2B;
        s.dividerColor = 0xFF6B4208;
        s.heroColor = 0xFFFFCB5E;

        s.tileFill = 0xFF1A1000;
        s.tileStroke = 0xFF7A4E0C;
        s.tileLabelColor = 0xFFFFB53C;
        s.tileFillFocused = 0xFFFFB53C;
        s.tileStrokeFocused = 0xFFFFD894;
        s.tileTextFocused = 0xFF140C00;
        s.textTiles = true;
        s.tileTextSp = 17f;
        s.tileTextAllCaps = true;
        // A CRT picks its selection out by inverting it, not by sliding a pane of glass around.
        s.floatingHighlight = false;

        s.pillText = 0xFFFFB53C;
        s.pillTextFocused = 0xFF140C00;
        s.pillStroke = 0xFF8A5A10;
        s.pillFill = 0x0DFFAA28;
        s.pillFillFocused = 0xFFFFB53C;

        s.dockBand = 0xFF130B00;
        s.dockEdge = 0xFF5A3A08;

        s.glassFillTop = 0x3DFFB53C;
        s.glassFillBottom = 0x14FFB53C;
        s.glassSpecular = 0x59FFD894;
        s.glassRim = 0xFFFFD894;
        s.glassGlow = 0xFFFFA014;

        s.fixedAmbient = 0xFFFF9600;
        s.monochrome = 0xFFFFB53C;
        s.scanlines = true;
        return s;
    }
}
