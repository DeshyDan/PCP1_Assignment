package parallelImplementation;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/* M. Kuttel 2023
 * Searcher class that lands somewhere random on the surfaces and 
 * then moves downhill, stopping at the local minimum.
 */

public class Search {
	private int id; // Searcher identifier
	private int pos_col, pos_row; // Position in the grid
	private int steps; // number of steps to end of search
	private boolean stopped; // Did the search hit a previous trail?

	private TerrainArea terrain;

	private class ComputeSearch extends RecursiveTask<Integer> {

		private int xMin;
		private int xMax;
		private int yMin;
		private int yMax;
		private int localValleyHeight;
		private TerrainArea terrain;

		public ComputeSearch(TerrainArea terrain, int xMin, int xMax, int yMin, int yMax) {
			this.terrain = terrain;
			this.xMin = xMin;
			this.xMax = xMax;
			this.yMin = yMin;
			this.yMax = yMax;

		}

		@Override
		protected Integer compute() {
			int localValleyHeight = Integer.MAX_VALUE;
			Direction next = Direction.STAY_HERE;
			int gridSize = (xMax - xMin) * (yMax - yMin);
			if (gridSize <= (100000)) {
				
				
				for (int x = xMin; x < xMax; x++) {
					for ( int y = yMin; y< yMax; y++) {
						while (terrain.visited(x, y) == 0) { // stop when hit existing path
							localValleyHeight = terrain.get_height(x, y);
							terrain.mark_visited(x, y, id); // mark current position as visited
							steps++;
							next = terrain.next_step(x, y); // returns the direction of the lowest height
							switch (next) {
								case STAY_HERE:
									return localValleyHeight; // found local valley
								case LEFT:
									// The lowest hieght is to the left of the current position
									x--;
									break;
								case RIGHT:
									// The lowest height is to the right of the current position
									x = x + 1;
									break;
								case UP:
									// The lowest height is above the current position
									y = y - 1;
									break;
								case DOWN:
									// The lowest height is below the current position
									y = y + 1;
									break;
							}
						}
					}
				}
				stopped = true;
				return localValleyHeight;
			} else {
				int midX = (xMin + xMax) / 2;
				int midY = (yMin + yMax) / 2;

				ComputeSearch topRight = new ComputeSearch(terrain, xMin, midX, yMin, midY);
				ComputeSearch topLeft = new ComputeSearch(terrain, xMin, midX, midY, yMax);
				ComputeSearch bottomRight = new ComputeSearch(terrain, midX, xMax, yMin, midY);
				ComputeSearch bottomLeft = new ComputeSearch(terrain, midX, xMax, midY, yMax);

				invokeAll(topLeft, topRight, bottomLeft, bottomRight);

				int minValleyHeight = Math.min(Math.min(topLeft.localValleyHeight, topRight.localValleyHeight),
						Math.min(bottomLeft.localValleyHeight, bottomRight.localValleyHeight));

				return minValleyHeight;

			}
		}
	}

	enum Direction {
		STAY_HERE,
		LEFT,
		RIGHT,
		UP,
		DOWN
	}

	public Search(int id, int pos_row, int pos_col, TerrainArea terrain) {
		this.id = id;
		this.pos_row = pos_row; // randomly allocated
		this.pos_col = pos_col; // randomly allocated
		this.terrain = terrain;
		this.stopped = false;
	}

	public int find_valleys() {
		int height = Integer.MAX_VALUE;
		// Direction next = Direction.STAY_HERE;

		ComputeSearch computeSearch = new ComputeSearch(terrain,0, terrain.rows,0,terrain.columns );

		try (ForkJoinPool pool = new ForkJoinPool()) {
			height =pool.invoke(computeSearch);
		}
		// while (terrain.visited(xVal, yVal) == 0) { // stop when hit existing path
		// 	height = terrain.get_height(xVal, yVal);
		// 	terrain.mark_visited(xVal, yVal, id); // mark current position as visited
		// 	steps++;
		// 	next = terrain.next_step(xVal, yVal); // returns the direction of the lowest height
		// 	switch (next) {
		// 		case STAY_HERE:
		// 			return height; // found local valley
		// 		case LEFT:
		// 			// The lowest hieght is to the left of the current position
		// 			xVal--;
		// 			break;
		// 		case RIGHT:
		// 			// The lowest height is to the right of the current position
		// 			xVal = xVal + 1;
		// 			break;
		// 		case UP:
		// 			// The lowest height is above the current position
		// 			yVal = yVal - 1;
		// 			break;
		// 		case DOWN:
		// 			// The lowest height is below the current position
		// 			yVal = yVal + 1;
		// 			break;
		// 	}
		// }
	
		return height;
	}

	public int getID() {
		return id;
	}

	public int getPos_row() {
		return pos_row;
	}

	public int getPos_col() {
		return pos_col;
	}

	public int getSteps() {
		return steps;
	}

	public boolean isStopped() {
		return stopped;
	}
}
