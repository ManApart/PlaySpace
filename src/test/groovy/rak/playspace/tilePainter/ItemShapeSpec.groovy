package rak.playspace.tilePainter

import rak.playspace.ui.tilePainter.ConnectedNeighbors
import rak.playspace.ui.tilePainter.ItemShape
import spock.lang.Specification

class ItemShapeSpec extends Specification {
	
	def "determine shape calculates properly"(ItemShape expected, int contiguousSides, boolean isCorner){
		given:
            ConnectedNeighbors neighbors = Mock()
		when:
			neighbors.getNumberOfConnectedNeighbors() >> contiguousSides
			neighbors.isCorner() >> isCorner
		then:
			assert expected == ItemShape.determineShape(neighbors) 
		where:
			expected				|	contiguousSides	|	isCorner
			ItemShape.ALL_EDGE		|	0				|	false
			ItemShape.THREE_EDGE	|	1				|	false
			ItemShape.CORNER		|	2				|	true
			ItemShape.TWO_EDGE		|	2				|	false
			ItemShape.ONE_EDGE		|	3				|	false
			ItemShape.NO_EDGE		|	4				|	false
	}
	

}
