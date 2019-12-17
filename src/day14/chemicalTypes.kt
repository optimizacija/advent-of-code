package day14

typealias Chemical = String
typealias ReactionResult = Pair<Chemical, Int>
typealias ReactionInput = ReactionResult
typealias ReactionComponents = List<ReactionInput>
typealias ReactionOutputLookup = MutableMap<Chemical, ReactionResult>
typealias ReactionInputsLookup = MutableMap<ReactionResult, ReactionComponents>


