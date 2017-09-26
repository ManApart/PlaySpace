package rak.playspace.tilePainter

import rak.playspace.ui.tilePainter.ItemShape
import spock.lang.Specification

class ItemShapeSpec extends Specification {
	
	def "determine shape calculates properly"(ItemShape expected, int contiguousSides, boolean isCorner){
		expect:
			assert expected == ItemShape.determineShape(contiguousSides, isCorner) 
		where:
			expected				|	contiguousSides	|	isCorner
			ItemShape.ALL_EDGE		|	0				|	true
			ItemShape.ALL_EDGE		|	0				|	false
			ItemShape.THREE_EDGE	|	1				|	true
			ItemShape.THREE_EDGE	|	1				|	false
			ItemShape.CORNER		|	2				|	true
			ItemShape.TWO_EDGE		|	2				|	false
			ItemShape.ONE_EDGE		|	3				|	true
			ItemShape.ONE_EDGE		|	3				|	false
			ItemShape.NO_EDGE		|	4				|	true
			ItemShape.NO_EDGE		|	4				|	false
	}
	

}
