package scanline;

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
		for (int y = 0; y < height; y++) {
			// 1. Alle Kanten, die die aktuelle y-Koordinate schneiden, in die Active Edge Table aufnehmen
			for (Edge edge : edgeTable) {
				if (edge.getYMin() == y) {
					activeEdgeTable.add(new Edge(edge));
				}
			}

			// 2. Kanten in der Active Edge Table sortieren
			activeEdgeTable.sort(Comparator.comparing(Edge::getxIntersect));

			// 3. Kantenpaare abarbeiten
			for (int i = 0; i < activeEdgeTable.size(); i += 2) {
				Edge edge1 = activeEdgeTable.get(i);
				Edge edge2 = activeEdgeTable.get(i + 1);
				// 4. Kantenpaar zeichnen
				graphics.drawLine((int) edge1.getxIntersect(), y, (int) edge2.getxIntersect(), y);
			}
			// 5. Kanten aus der Active Edge Table entfernen, die die aktuelle y-Koordinate verlassen
			LinkedList<Edge> edgesToRemove = new LinkedList<>();
			for (Edge edge : activeEdgeTable) {
				if (edge.getyMax() == y) {
					edgesToRemove.add(edge);
				}
			}
			activeEdgeTable.removeAll(edgesToRemove);

			// 6. x-Koordinaten der verbleibenden Kanten aktualisieren
			for (Edge edge : activeEdgeTable) {
				edge.setxIntersect(edge.getxIntersect() + edge.getmReci());
			}
		}
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void addEdges(LinkedList<Edge> edges) {
		edgeTable.addAll(edges);
	}
}
