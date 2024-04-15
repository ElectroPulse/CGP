package cohensutherland;

/**
 * Konstanten für die Bits im Output-Code.
 * 
 * Über bitweises OR können damit Bits im Output-Code gesetzt und über bitweises
 * AND Bits abgefragt werden.
 */
public interface Area {
	static final int LTXMIN = 1; // 0001
	static final int GTXMAX = 2; // 0010
	static final int LTYMIN = 4; // 0100
	static final int GTYMAX = 8; // 1000
}