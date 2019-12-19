package day14

typealias Chemical = String
typealias ReactionResult = Pair<Chemical, Long>
typealias ReactionComponent = ReactionResult
typealias ReactionComponents = List<ReactionComponent>
typealias ReactionResultLookup = Map<Chemical, ReactionResult>
typealias ReactionComponentLookup = Map<ReactionResult, ReactionComponents>


