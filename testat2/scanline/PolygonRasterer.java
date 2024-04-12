import java.awt.Graphics;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Rastert Polygone mit Scanline-Verfahren.
 * 
 * Zur Vereinfachung nehmen wir an, dass sich Polygone immer komplett im
 * Viewport befinden, den Rand also nicht schneiden.
 */
public class PolygonRasterer {
	/** Zum Zeichnen */
	private Graphics graphics;
	/** Höhe des Zeichenfensters */
	private int height;
	/** Die Edge Table */
	private LinkedList<Edge> edgeTable = new LinkedList<>();
	/** Die Active Edge Table */
	private LinkedList<Edge> activeEdgeTable = new LinkedList<>();

	public PolygonRasterer(int height) {
		this.height = height;
	}

	/**
	 * Umsetzung des Scan-Line-Verfahrens
	 */
	public void rasterize() {
		// TODO: Ihr Code hier...
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void addEdges(LinkedList<Edge> edges) {
		edgeTable.addAll(edges);
	}
}
