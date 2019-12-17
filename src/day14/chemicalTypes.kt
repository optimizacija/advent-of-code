package day14

typealias Chemical = String
typealias ReactionResult = Pair<Chemical, Int>
typealias ReactionComponent = ReactionResult
typealias ReactionComponents = List<ReactionComponent>
typealias ReactionResultLookup = MutableMap<Chemical, ReactionResult>
typealias ReactionComponentLookup = MutableMap<ReactionResult, ReactionComponents>


